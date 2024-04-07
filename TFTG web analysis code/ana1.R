rm(list=ls())
x=x=c('')
adjust=0
threshold=0.05
setwd("/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/")
library(data.table)
geneset=data.frame(fread("input.txt",header=T,sep="\t"))
result=matrix(data=NA, nrow = length(geneset[,1]), ncol = 7, byrow = TRUE)
colnames(result)=c("TF","Method","Biosample_name","Core_enrichment","GeneNumber","PValue","FDR")
enrichment<-function(x,adjust,threshold) {
    input<-c(x)
    n<-length(input)
    for (i in 1:length(geneset[,1])) {
        gene=unlist(strsplit(geneset[i,2],split=";"))
        result[i,1]<-geneset[i,1]      
        result[i,2]<-geneset[i,4]
        result[i,3]<-geneset[i,5]
        inter<-intersect(gene,x)
        result[i,4]<- paste(inter,collapse=";")
        t<-length(gene)
        r<-length(inter)
        result[i,5]<-r
        result[i,6]<-signif(phyper(r-1,t,20000-t,n,lower.tail=FALSE),3)
    }
    result[,7]<-signif(p.adjust(result[,6],method="fdr",n=length(geneset[,1])),3)
    if (adjust==1) {
        result<-result[which(as.numeric(result[,7])<as.numeric(threshold)),]
        result<-result[sort.list(as.numeric(result[,7]),decreasing=F),]
    } else {
        result<-result[which(as.numeric(result[,6])<as.numeric(threshold)),]
        result<-result[sort.list(as.numeric(result[,6]),decreasing=F),]
    }

    write.table(result,"result.txt",row.names=F,quote=F,sep="\t")
}
enrichment(x,adjust,threshold)

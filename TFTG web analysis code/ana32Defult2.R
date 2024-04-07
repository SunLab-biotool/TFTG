rm(list=ls())
adjust=0
threshold=0.1
gene
tfs
outpath="file"
userID="name"
library(data.table)
all=data.frame(fread(paste0(outpath,"TF_all_target.txt",sep=""),header=T,sep="\t"))
geneset=all[which(all[,1]%in%y),]

result=matrix(data=NA, nrow = length(geneset[,1]), ncol = 5, byrow = TRUE)
colnames(result)=c("TF","Annotated_Gene","Annotated_Gene_number","P_Value","FDR")

enrichment<-function(x,adjust,threshold) {
    input<-x
    n<-length(input)
    for (i in 1:length(geneset[,1])) {
        gene=unlist(strsplit(geneset[i,2],split=";"))
		inter<-intersect(gene,input)
		
        result[i,1]<-geneset[i,1]
		result[i,2]<-paste(inter,collapse=";")
		result[i,3]<-length(inter)
        t<-length(gene)
        r<-length(inter)
        result[i,4]<-signif(phyper(r-1,t,70000-t,n,lower.tail=FALSE),3)
    }
    result[,5]<-signif(p.adjust(result[,4],method="fdr",n=length(geneset[,1])),3)
    if (adjust==1) {
        result<-matrix(result[which(as.numeric(result[,5])<as.numeric(threshold)),],nrow=length(which(as.numeric(result[,5])<as.numeric(threshold))))
        result<-matrix(result[sort.list(as.numeric(result[,5]),decreasing=F),],nrow=length(which(as.numeric(result[,5])<as.numeric(threshold))))
    } else {
	    
        result<-matrix(result[which(as.numeric(result[,4])<as.numeric(threshold)),],nrow=length(which(as.numeric(result[,4])<as.numeric(threshold))))
        result<-matrix(result[sort.list(as.numeric(result[,4]),decreasing=F),],nrow=length(which(as.numeric(result[,4])<as.numeric(threshold))))
    }
    result<-data.frame(result)

    result[,6]<-1:nrow(result)
    result<-result[,c(1,6,2:5)]
    colnames(result)=c("TF","Rank","Annotated_Gene","Annotated_Gene_number","P_Value","FDR")
    write.table(result,paste0(outpath,userID,"/result32.txt",sep=""),row.names=F,quote=F,sep="\t")
    return(result)
}
res=enrichment(x,adjust,threshold)


























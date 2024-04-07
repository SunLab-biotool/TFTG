rm(list=ls())
adjust=fdr
threshold=yuzhi
genes
outpath="/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/"
outpath2="name"
library(data.table)
geneset=data.frame(fread(paste0(outpath,outpath2,"/pathway_information.txt",sep=""),header=T,sep="\t"))
TF_info=data.frame(fread(paste0(outpath,"tF_ALL.txt",sep=""),header=T,sep="\t"))
result=matrix(data=NA, nrow = length(geneset[,1]), ncol = 10, byrow = TRUE)
colnames(result)=c("pathway_ID","pathway_name","pathway_source","Annotated_Gene","Annotated_Gene_number","Total_Gene_number","The_Terminal_TF","TF_number","P_Value","FDR")

enrichment<-function(x,adjust,threshold) {
    input<-x
    n<-length(input)
    for (i in 1:length(geneset[,1])) {
        gene=unlist(strsplit(geneset[i,6],split=";"))
        result[i,1]<-geneset[i,1]
        result[i,2]<-geneset[i,2]
        result[i,3]<-geneset[i,3]
        inter<-intersect(gene,input)
        result[i,4]<-paste(inter,collapse=";")
        result[i,5]<-length(inter)
        result[i,6]<-length(gene)
        inter_tf=intersect(gene,TF_info[,1])
        result[i,7]<-paste(inter_tf,collapse=";")
        result[i,8]<-length(inter_tf)
        t<-length(gene)
        r<-length(inter)
        result[i,9]<-signif(phyper(r-1,t,20000-t,n,lower.tail=FALSE),3)
    }
    result[,10]<-signif(p.adjust(result[,9],method="fdr",n=length(geneset[,1])),3)
    if (adjust==1) {
	    lo=which(as.numeric(result[,10])<as.numeric(threshold))
        if(length(lo)>0)
		{
        result<-matrix(result[lo,],nrow=length(lo))
        result<-matrix(result[sort.list(as.numeric(result[,10]),decreasing=F),],nrow=length(lo))
		}else
		{
		result=matrix(data=NA, nrow = 1, ncol = 10, byrow = TRUE)
		result=result[-1,]
		}
    } else {
	    lo=which(as.numeric(result[,9])<as.numeric(threshold))
		if(length(lo)>0)
		{
        result<-matrix(result[lo,],nrow=length(lo))
        result<-matrix(result[sort.list(as.numeric(result[,9]),decreasing=F),],nrow=length(lo))
		}else
		{
		result=matrix(data=NA, nrow = 1, ncol = 10, byrow = TRUE)
		result=result[-1,]
		}
    }
    colnames(result)=c("pathway_ID","pathway_name","pathway_source","Annotated_Gene","Annotated_Gene_number","Total_Gene_number","The_Terminal_TF","TF_number","P_Value","FDR")

    write.table(result,paste0(outpath,outpath2,"/pathwayresult.txt",sep=""),row.names=F,quote=F,sep="\t")
    return(result)
}

res=enrichment(x,adjust,threshold)

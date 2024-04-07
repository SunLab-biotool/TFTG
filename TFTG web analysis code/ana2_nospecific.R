rm(list=ls())
library("getopt")
library("igraph")
Args<-commandArgs(TRUE)
shu=7      ####你给
outpath="/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/"
file1=paste0(outpath,'non_specific.Rdata',sep="")###########################¸ÄÂ·¾¶ºÍ·½·¨##########file1ÖÐ´æÒ»¸ö·½·¨ÎÄ¼þ(ÃüÃûÎªdata)ºÍÒ»¸ö´øÓÐ01µÄÒ»ÁÐ»ùÒòÎÄ¼þ(ÃüÃûÎªattribute)£¬1ÊÇTF
load(file=file1)
usersinput<-read.table(paste(outpath,"tuo.txt",sep=""),header=F,sep="\t")##############用户输入的
a<-attribute[which(attribute[,2]==1),1]

data1=data[which(data[,3]>=shu),]
tf=intersect(data1[,1],usersinput[,1])
aaa=data1[which(data1[,1]%in%tf),]
gene=usersinput[-which(usersinput[,1]%in%tf),1]
bbb=data1[which(data1[,2]%in%gene),]
b<-rbind(aaa,bbb)
e<-data.frame(from=b[,1],to=b[,2])
g<-graph_from_data_frame(e,directed = FALSE)
h=degree(g)
i=betweenness(g)
j=closeness(g)
ta=cbind(h,i,j)
new0=cbind(ta,"0","0")

new0[which(rownames(new0)%in%a),4]="1"
new0[which(rownames(new0)%in%usersinput[,1]),5]="1"
haha=cbind(rownames(new0),new0[,1:5])
colnames(haha)=c("Gene","Degree","Betweenness","Closeness","TF or not","Input or not")
write.table(b[,1:2],paste(outpath,"net.txt",sep=""),quote=F,row.name=F,col.name=F,sep="\t")##############路径########net
write.table(haha,paste(outpath,"table_gene.txt",sep=""),quote=F,row.names=F,sep="\t")##############路径########table


library("getopt")
library("igraph")
Args<-commandArgs(TRUE)
method='a'      ####你给
cell='b'         ##############你给
outpath="/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/"
outpath2="folder"
file1=paste0(outpath,method,'.Rdata',sep="")###########################改路径和方法##########file1中存一个方法文件(命名为data)和一个带有01的一列基因文件(命名为attribute)，1是TF
load(file=file1)
usersinput<-read.table(paste(outpath,outpath2,"/tuo.txt",sep=""),header=F,sep="\t")##############用户输入的
a<-attribute[which(attribute[,2]==1),1]

data1=data[which(data[,4]%in%cell),]
tf=intersect(data1[,1],usersinput[,1])
aaa=data1[which(data1[,1]%in%tf),]
bbb=data1[which(data1[,2]%in%usersinput[,1]),]
b<-rbind(aaa,bbb)
lob=which(b[,1]%in%b[,2])
if(length(lob)>0)
{
b=b[-lob,]
}else
{
b=b
}


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
write.table(b[,1:2],paste(outpath,outpath2,"/net.txt",sep=""),quote=F,row.name=F,col.name=F,sep="\t")##############路径########net
write.table(haha,paste(outpath,outpath2,"/table_gene.txt",sep=""),quote=F,row.names=F,sep="\t")##############路径########table



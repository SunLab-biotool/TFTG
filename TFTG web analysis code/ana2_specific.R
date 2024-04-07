library("getopt")
library("igraph")
Args<-commandArgs(TRUE)
method='SE'      ####���
cell='C4-2B'         ##############���
outpath="/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile/"
file1=paste0(outpath,method,'.Rdata',sep="")###########################��·���ͷ���##########file1�д�һ�������ļ�(����Ϊdata)��һ������01��һ�л����ļ�(����Ϊattribute)��1��TF
load(file=file1)
usersinput<-read.table(paste(outpath,"tuo.txt",sep=""),header=F,sep="\t")##############�û������
a<-attribute[which(attribute[,2]==1),1]

data1=data[which(data[,4]%in%cell),]
tf=intersect(data1[,1],usersinput[,1])
aaa=data1[which(data1[,1]%in%tf),]
gene=usersinput[-which(usersinput[,1]%in%tf),1]
bbb=data1[which(data1[,2]%in%gene),]
b<-rbind(aaa,bbb)
lo=which(b[,1]==b[,2])
if(length(lo)>0)
{
bb=b[-lo,]
}else
{
bb=b
}
e<-data.frame(from=bb[,1],to=bb[,2])
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
write.table(bb[,1:2],paste(outpath,"net.txt",sep=""),quote=F,row.name=F,col.name=F,sep="\t")##############·��########net
write.table(haha,paste(outpath,"table_gene.txt",sep=""),quote=F,row.names=F,sep="\t")##############·��########table



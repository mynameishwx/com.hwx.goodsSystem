package com.hwx.goodsSystem.util;

import com.hwx.goodsSystem.entity.sensitive;
import com.hwx.goodsSystem.service.sensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 敏感词过滤
 */
@Component
@Slf4j
public class sorting {


    @Autowired
    private sensitiveService sensitiveService;
    /**
     * 根节点
     */
    private Node  startNode=new Node();


    /**
     * 搜索根节点
     */
    private Node searchNode=new Node();

    /**
     * 初始化敏感词
     */
    public  void  initNode(){
        List<sensitive> sensitiveList=new ArrayList<>();
        sensitiveList=sensitiveService.getSensitive();
        if(sensitiveList.size()==0){
            log.warn("初始化节点失败,敏感词为空!");
        }
        Iterator<sensitive> sensitiveIt=sensitiveList.iterator();
        while (sensitiveIt.hasNext()){
            sensitive sensitive=new sensitive();
            sensitive=sensitiveIt.next();
            String SENSITIVE=sensitive.getSensitiveText();
            if(SENSITIVE.length()!=0){
                Node Node=startNode;
                for (int i = 0; i < SENSITIVE.length(); i++) {
                    Character string=SENSITIVE.charAt(i);
                    Node oneNode=new Node();
                    oneNode=Node.getSonNode(string);
                    if(oneNode==null){
                        oneNode =new Node();
                        Node.addSonNode(string,oneNode);
                    }
                    Node=oneNode;
                    if(i==SENSITIVE.length()-1){
                        Node.setIsEndNode(true);
                    }
                }
            }else {
                log.warn("读取ID为:"+sensitive.getId()+"的敏感词为空！该敏感词未初始化到节点");
            }
        }

    }

    /**
     * 敏感词过滤
     * @param startString
     * 过滤之前的字符串
     * @return
     */
    public String sorting(String startString){


        /**
         * 根节点
         */
        Node Node=startNode;

        /**
         * 判断是否已经初始化敏感词
         */
        if(Node.getMapSize()==0){
            /**
             * 敏感词从数据库中取出
             */
            this.initNode();
        }

        /**
         * 可能是需要和谐的字符
         */
        String mayString="";

        /**
         * 替代字符用于匹配上但是没有完全匹配之后用于更换的字符
         */
        String replaceString="";

        /**
         * 没问题的字符
         */
        String intoString="";

        /**
         * 过滤
         */
        for (int i = 0; i < startString.length(); i++) {
            Node indexNode=new Node();
            Character string=startString.charAt(i);
            indexNode=Node.getSonNode(string);
            if(indexNode!=null){
                Node=indexNode;
                mayString="*";
                replaceString=replaceString+string;
                for (int j = i+1; j < startString.length(); j++) {
                     indexNode=Node.getSonNode(startString.charAt(j));
                     if(indexNode!=null){
                         mayString=mayString+"*";
                         replaceString=replaceString+startString.charAt(j);
                         Node=indexNode;
                         if(Node.getIsEndNode()){
                             intoString=intoString+mayString;
                             mayString="";
                             replaceString="";
                             Node=startNode;
                             i=j;
                             break;
                         }
                     }else {
                         intoString=intoString+replaceString;
                         replaceString="";
                         i=j;
                         mayString="";
                         Node=startNode;
                         break;
                     }
                }
            }else {
               intoString=intoString+string;
            }
        }
        return intoString;
    }

    /**
     * 节点
     */
    private class Node{

        /**
         * 是否是最后一个节点
         */
        private boolean  isEndNode=false;

        /**
         * 子节点
         */
        private Map<Character,Node> sonNode=new HashMap<>();

        /**
         * 添加一个子节点
         * @param string
         * @param node
         */
        public void  addSonNode(Character string,Node node){
            this.sonNode.put(string,node);
        }

        /**
         * 获取子节点
         * @param string
         * @return
         */
        public Node  getSonNode(Character string){
            return sonNode.get(string);
        }

        /**
         * 获取子节点大小
         * @return
         */
        public int getMapSize(){
            return this.sonNode.size();
        }

        /**
         * 获取节点状态
         * @return
         */
        public boolean getIsEndNode(){
            return this.isEndNode;
        }

        /**
         * 写入节点状态
         * @param st
         */
        public void setIsEndNode(boolean st){
            this.isEndNode=st;
        }
    }


}

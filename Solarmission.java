/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Daniel Silvestre Rosales Aguilar 214661
 */
import java.io.File;
import java.util.Random;
import java.io.FileWriter;
import java.util.Scanner;
public class Solarmission {
    public static boolean randomError(int prob,int a, int b){
        // prob is the integer percentage of faillure
        Random rd= new Random(System.currentTimeMillis());
        if(prob>rd.nextInt(100)){
            return a>b;
        }else{
            return a<=b;
        }
        
    }
    public static void swap(int[] arr,int a,int b){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
        
    }
    public static void quickSort(int[] arr,int prob,int l,int r){
        if(l<r){
        int li=l;
        int ri=r;
        while(li<ri){
            if(randomError(prob,arr[li+1],arr[li])){
                swap(arr,li,li+1);
                li++;
            }else{
                swap(arr,li+1,ri);
                ri--;
            }
        }
        quickSort(arr,prob,l,li-1);
        quickSort(arr,prob,li+1,r);
        }
    }   
    
    public static void bubleeSort(int []arr,int prob,int n){
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-(1+i);j++){
               if(randomError(prob,arr[j+1],arr[j])){
                   swap(arr,j+1,j);
               } 
            }
        }
    }
    
    public static void mergeSort(int []arr,int prob,int l, int r){
        if(l<r){
            int mid=l+((r-l)/2);
            mergeSort(arr,prob,l,mid);
            mergeSort(arr,prob,mid+1,r);
            int li=l;
            int ri=mid+1;
            int k=0;
            int []auxi=new int[(r-l)+1];
            while(li<=mid && ri<=r){
                if(randomError(prob,arr[li],arr[ri])){
                    auxi[k]=arr[li];
                    li++;
                }else{
                    auxi[k]=arr[ri];
                    ri++;
                }
                k++;
            }
            if(li>mid){
                while(ri<=r){
                    auxi[k]=arr[ri];
                    k++;
                    ri++;
                }
            }else{
                while(li<=mid){
                    auxi[k]=arr[li];
                    k++;
                    li++;
                }
            }
            for(int h=l;h<=r;h++){
                arr[h]=auxi[h-l];
            }
            
        }
    }
    
    public static void insertionSort(int []arr,int prob,int n){
        int elem;
        int j;
        for(int i=1;i<n;i++){
            elem=arr[i];
            j=0;
            while(j<n&&randomError(prob,arr[j],elem)){
                j++;
            }
            for(int k=i;k>j;k--){
                arr[k]=arr[k-1];
            }
            arr[j]=elem;
        }
    }
    
    public static void selectionSort(int []arr,int prob,int n){
        int max_pos,max_value;
        for(int i=n-1;i>0;i--){
            max_pos=0;
            max_value=arr[0];
            for(int j=1;j<=i;j++){
                if(randomError(prob,max_value,arr[j])){
                    max_value=arr[j];
                    max_pos=j;
                }
            }
            swap(arr,max_pos,i);
        }
    }
    public static int calcError(int[] arr,int n){
        int res=0;
        for(int i=0;i<n;i++)res+= Math.abs(arr[i]-(i+1));
        return res;
    }
    public static void main(String []args){
        try{
        File fl=new File("src\\Clases\\permutaciones.txt");
        Scanner rd=new Scanner(fl);
        FileWriter writer=new FileWriter("src\\Clases\\resultados.csv");
        int [] mainArray= new int[1001];
        int [] aux=new int[1001];
        int tam=1000;//tamanio de las permutaciones
        //for(int prob=0;prob<50;prob+=5 ){
        for(int prob=10;prob<=50;prob+=10){
        writer.append("quickSort"+String.valueOf(prob)+",bubbleSort"+String.valueOf(prob)+",insertionSort"+String.valueOf(prob)+",selectionSort"+String.valueOf(prob)+",mergeSort"+String.valueOf(prob)+"\n");
        for(int i=0;i<100;i++){
            for(int j=0;j<1000;j++){
               mainArray[j]=rd.nextInt();
               aux[j]=mainArray[j];
            }
            quickSort(aux,prob,0,tam-1);
            writer.append(Integer.toString(calcError(aux,tam))+",");
            
            System.arraycopy(mainArray, 0, aux, 0, tam);
            bubleeSort(aux,prob,tam);
            writer.append(Integer.toString(calcError(aux,tam))+",");
            
            System.arraycopy(mainArray, 0, aux, 0, tam);
            insertionSort(aux,prob,tam);
            writer.append(Integer.toString(calcError(aux,tam))+",");
            
            
            System.arraycopy(mainArray, 0, aux, 0, tam);
            selectionSort(aux,prob,tam);
            writer.append(Integer.toString(calcError(aux,tam))+",");
            
            System.arraycopy(mainArray, 0, aux, 0, tam);
            mergeSort(aux,prob,0,tam-1);
            writer.append(Integer.toString(calcError(aux,tam))+",");
            
            writer.append("\n");
            writer.flush();
        }
        }
        //}
        writer.close();
        rd.close();
        }catch(Exception E){
            System.out.println(E);
        }
    }
}

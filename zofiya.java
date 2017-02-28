package com.company;

zofiya

import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;
import java.util.Random;

public class Main {

    static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        int[] arr = {12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43, 50};
        int m = 7;
        System.out.println(findMinDiff(arr, m));
    }
    // תרגיל 2:
    static  boolean searchInSortedMatrix (int [][]mat , int x)
    {
       int n = mat.length;
       int i=0, j=n-1;//תאתחל את האינדקס לפינה הימנית השמאלית באלמנט
        while (i<n && j<=0){
            if (mat[i].length != n)
                throw new InvalidParameterException("row"+ i + "is not length" + n);
            if(mat[i][j]==x)
                return true;
            if(mat[i][j]>x)
                j--;
            else
                i++;
        }
        return  false;
    }


    //תרגיל 1:


    //פונקציית ערבול רנדומלי
    static int randomizedPartition(int[] arr,int low,int high){
        if(high==low)
            return low ;
        Random random= new Random(System.currentTimeMillis());//לא שמים ברנדום מספר כדי שכל פעם הוא יביא לנו סדרת מספרים אחרת, אם שמים אותו מספר הוא חוזר תמיד על אותה רשימה אקראית
        int pos=random.nextInt(high-low)+low;// מקבלים מספר אקראי בין הגבוהה לנמוך
        int temp=arr[high];
        arr[high]=arr[pos];
        arr[pos]=temp;
        return partition(arr,low,high);
    }
    //מיון מהיר
    //נעשה איזה ערבול רנדומלי כדי שתמיד המקרה הממוצע יהיה nlogn נעשה פונקציית ערבול רנדומלי
    static void quickSort (int [] arr, int low, int high){
        if(low<high){//אם כן שולחת לפונקצית פרטישיין ושומרת את התוצאה
            int pi= partition(arr,low,high);
            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }

    }

    static int findMinDiff(int [ ]arr, int m){
        if(m==0 || arr.length==0)
            return 0;
        if (arr.length <m)
            return  -1;
        quickSort(arr, 0, arr.length-1);
        int minDiff = Integer.MAX_VALUE;
       // int first , last ;
        for (int i=0; i+m-1<arr.length;i++){
            int diff = arr[i+m-1] - arr[i];
            if (diff < minDiff){
                minDiff = diff;
              //  first = i;
              //  last = i+m-1;
            }
        }
       // return  arr[last] - arr[first];
        return minDiff;

    }

    //פונקצית עזר למיון מהיר שאומרת לכל מערך אני אקח איבר אחד (לדוג האחרון)
    //ובסוף העבודה האיבר ימוקם במקום הנכון כל האיברים שקטנים ממנו יהיה לשמאלו וכל הגדולים ממנו יהיו לימינו
    //שומרים במשתנה עזר כמה איברים קטנים מאיבר הציר
    static int partition(int[] arr,int low,int high){
        int pivot= arr[high]; //בתחילה האיבר ציר הוא האיבר האחרון
        //לולאה שעוברת על ערכי המערך
        // אם אתה קטן או שווה מהאיבר ציר רוצים להדביק אותו הכי שמאלה
        //נקדם את I הוא גדל שמצאתי איבר קטן יותר מהאיבר ציר
        //מחליף בין האיברים
        //מקדמים את הI שוב ומסדרים את מיקומו הסופי של איבר הציר
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(arr[j] <= pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }
    //שינינו את הI לK
    //מציאת האיבר הI בגודלו= מציאת I האיברים הקטנים ביותר המערך
    //מחלק את המערך ל2 וכל פעם מחפש את האיבר במקום המתאים
    //אם האינדקס שמצאנו קטן יותר מהI אז מורידים מהI את האינדקס וזה מס האיבר החדש שאנחנו רוצים למצוא
    static int quickSelect(int [] arr,int l,int r, int k ){
        if(k>0&& k<=r-l+1){// כל עוד הL קטן מהR
            int pos =randomizedPartition(arr,l,r);
            if(pos-l ==k-1)//הפונקציה פרטישיין חילקה לי בול במקום
                return arr[pos];
            if (pos-l>k-1)//שקיבלנו מיקום גדול מהאיבר במיקום שאנחנו מחפשים
                return quickSelect(arr,l,pos-1,k);
            return quickSelect(arr, pos+1,r,k-pos+l-1);// פוס נותן מספר של מיקום על המערך המקורי-בכל שליחה לפונקציה ולכן צריך להוסיף את הl
        }
        return Integer.MAX_VALUE;//  כל האיברים במערך קטנים מהאיבר שאתה מבקש לכן מחזיר איזה שהוא איבר גדול
    }
}



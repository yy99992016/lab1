import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
class class_Str{
	int a = 0;
	String b[] = new String[10];
	boolean jus1 = true;
}

public class new_lab1 {

	public static void main(String[] args) {
		class_Str Exp[] = new class_Str[10];
		HashMap map = new HashMap();
		int a[][] = new int[10][10],i = 0,j = 0,k = 0;//×¢ÊÍ
		int varn[] = new int[10];
		int varn1[] = new int[10];
		boolean jus = true;
		char b[][] = new char[10][10];
		String b1[] = new String[10];
		String b2[] = new String[10];
		Scanner sc = new Scanner(System.in);
		String Expression = sc.nextLine();
		String[] aa = Expression.split("\\+");
		String regex = "\\d*";
		String regExp="\\D";
		String regjus="^[a-z\\d*\\+\\*]+$";
		String regjus1 = "[a-z]";
		if(!Expression.matches(regjus))
		{
			System.out.print("Error");
		}
		else{
		for(i = 0;i < aa.length;i++)
		{
			Pattern p = Pattern.compile(regex);
			Pattern p1 = Pattern.compile(regExp);
			Matcher m = p.matcher(aa[i]);
			Matcher m1 = p1.matcher(aa[i]);
		    j = 0;
		    k = 0;
			while(m1.find()){
		    	 b2[j] = m1.group();
		    	 if(j >= 1&&b2[j].matches(regjus1)&&b2[j-1].matches(regjus1)){
		    		 jus = false;
		    	 }
		    	 if(b2[j] != "*"){
		    		 Exp[i].b[j] = b2[j];
		    	 }
		    	 j+=1;
		     }
			Exp[i].b[j] = "0";
			Exp[i].a = 1;
			while (m.find()) {
				if (!"".equals(m.group())){
				b1[i] = m.group();
				a[i][k] = Integer.parseInt(b1[i]);
				Exp[i].a *= a[i][k];
				}
			}
		}
		if(jus == true){
		for(i = 0;i < aa.length;i++)
		{
			System.out.print(aa[i]);
			if(i != aa.length-1)
			{
				System.out.print('+');
			}
		}
		}
		else{
			System.out.print("Error");
		}
	}
	Scanner sc1 = new Scanner(System.in);
	String Expression1 = sc1.nextLine();
	if(Expression1.startsWith("!")){
		Expression1 = Expression1.replace("!","");
		if(Expression1.startsWith("simplify")){
			Expression1 = Expression1.replace("simplify ","");
			if(Expression1 == ""){
				for(i = 0;i < aa.length;i++){
					System.out.print(aa[i]);
					if(i != aa.length-1){
						System.out.print('+');
					}
				}
			}
			else{
				Pattern p = Pattern.compile(regex);
				Pattern p1 = Pattern.compile(regjus1);
				Matcher m = p.matcher(Expression1);
				Matcher m1 = p1.matcher(Expression1);
				j = 0;
				i = 0;
				while(m1.find()){
			    	 b2[j] = m1.group();
			    	 j+=1;
			     }
				while (m.find()) {
					if (!"".equals(m.group())){
					b1[i] = m.group();
					map.put(b2[i],b1[i]);
					i+=1;
					}
				}
				Iterator keys = map.keySet().iterator();
				for(i = 0;i < 10;i++){
					if(Exp[i].a != 0){
					for(j = 0;j < 10;j++){
						if(Exp[i].b[j] != "0"){
							while(keys.hasNext()){
								String key = (String)keys.next();
								if(Exp[i].b[j].equals(key)){
									String h = (String)map.get(Exp[i].b[j]);
									Exp[i].a *= Integer.parseInt(h);
									Exp[i].b[j] = "0";
								}
							}
						}
					}
					}
					System.out.print(Exp[i].a);
					for(j = 0;j < 10;j++){
						if(Exp[i].b[j] != "0"){
							System.out.print("*");
							System.out.print(Exp[i].b[j]);
						}
					}
					if(Exp[i+1].a != 0){
						System.out.print('+');
					}
				}
			}
		}
	}
}
}

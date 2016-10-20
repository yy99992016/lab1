import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lab1 {

	public static void main(String[] args) {
		int a[][] = new int[10][10],i = 0,j = 0,k = 0;
		int varn[] = new int[10];
		int varn1[] = new int[10];
		boolean jus = true;
		char b[][] = new char[10][10];//×¢ÊÍ
		String b1[] = new String[10];
		String b2[] = new String[10];
		char varc[] = new char [10];
		char varc1[][] = new char[10][10];
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
		    	 if(j >= 1&&b2[j].matches(regjus1)&&b2[j-1].matches(regjus1))
		    	 {
		    		 jus = false;
		    	 }
		    	 b[i][j]=b2[j].charAt(0);
		    	 j+=1;
		       }
			b[i][j] = '0';
			while (m.find()) {
				if (!"".equals(m.group())){
				b1[i] = m.group();
				a[i][k] = Integer.parseInt(b1[i]);
				k+=1;
				}
			}
			a[i][k] = 0;
		}
		b[i][0] = '0';
		a[i][0] = 0;
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
	if(Expression1.startsWith("!"))
	{
		String Expression2 = Expression1.replace("!","");
		if(Expression2.startsWith("simplify"))
		{
			Expression2 = Expression2.replace("simplify ","");
			if(Expression2 == "")
			{
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
				Pattern p = Pattern.compile(regex);
				Pattern p1 = Pattern.compile(regjus1);
				Matcher m = p.matcher(Expression2);
				Matcher m1 = p1.matcher(Expression2);
				j = 0;
				i = 0;
				while(m1.find()){
			    	 b2[j] = m1.group();
			    	 varc[j] = b2[j].charAt(0);
			    	 j+=1;
			     }
				varc[j] = '0';
				while (m.find()) {
					if (!"".equals(m.group())){
					b1[i] = m.group();
					varn[i] = Integer.parseInt(b1[i]);
					i+=1;
					}
				}
				varn[i] = 0;
				varc1 = b;
				for(i = 0;i < b.length;i++)
				{
					varn1[i] = 1;
					if(b[i][0] != '0'){
						for(j = 0;j < b[i].length;j++){
							if(b[i][j] != '0'){
								for(k = 0;k < varc.length;k++){
									if(varc[k] != '0' && varc[k] == b[i][j]){
										varn1[i] *= varn[k];
										b[i][j] = '1';
									}
									else if(varc[k] == '0'){
										break;
									}
								}
							}
						}
					}
					else if(b[i][0] == '0'){
						break;
					}
				}
				for(i = 0;i < b.length;i++){
					if(b[i][0] != '0'||a[i][0] != 0){
						for(j = 0;j < a[i].length;j++){
							if(a[i][j] != 0){
								varn1[i] *= a[i][j];
							}
						}
						if(varn1[i] != 1){
							System.out.print(varn1[i]);
						}
						for(j = 0;j < b[i].length;j++){
							if(b[i][j] != '0'){
								if(b[i][j] != '1'){
									System.out.print(b[i][j]);
								}
							}
							else
							{
								break;
							}
						}
						if(b[i+1][0] != '0'||a[i+1][0] != 0){
							System.out.print("+");
						}
					}
					else{
						break;
					}
				}
			}
		}
		else if(Expression2.startsWith("d/d"))
		{
			
		}
	}
	else{
		System.out.print("Error") ;//System out;;

	}
	}
}

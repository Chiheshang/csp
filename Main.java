/*2019.2.7 csp2012-01
这次题没全对，只拿了60……
主要是因为看着输入测试只有666，就以为不会测试到700
实际由于有跳过，导致总轮数是超过700的
*/
import java.util.Scanner;

public class Main {
	public static boolean judge(final int i) {
		int x=0;
		if(i%10==7) x=1;
		if(i%7==0) x=1;
		if(((i%100)/10)==7) x=1;
		if(i/100==7) x=1;
		if(x==1) return true;
		else return false;
	}

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		final Scanner in= new Scanner(System.in);
		final int x=in.nextInt();//num of times
		final int [] num=new int[4];//甲乙丙丁
		int num_judge=0;//跳过的数字
		int n=0;
		for(int i=1;i<(x+1+num_judge);i++) {
			if(i==637) {
				final int a=0;
			}
			if(judge(i)) {
				num[n]++;
				num_judge++;
			}
			n=((n==3)?0:n+1);
		}
		for(final int n1:num) {
			System.out.println(n1);
		}
	}

}

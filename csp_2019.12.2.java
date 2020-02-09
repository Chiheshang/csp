import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in= new Scanner(System.in);
		int num=in.nextInt();
		boolean exist=false;
		int [] num_point=new int [5];
		int [][] rubbish=new int[num][2];
		for(int i=0;i<num;i++) {//读入垃圾位置
			rubbish[i][0]=in.nextInt();
			rubbish[i][1]=in.nextInt();
		}
		for(int i=0;i<num;i++) {//找出四周都有垃圾的
			if(judge(i,rubbish)) {
				exist=true;
				num_point[num(i,rubbish)]++;
			}
		}
		if(exist) {
			for(int k:num_point) {
				System.out.println(k);
			}
		}
		else System.out.print("不存在可选地址。");
		
	}

	private static int num(int i, int[][] rubbish) {//数得分数
		// TODO Auto-generated method stub
		int x=rubbish[i][0];
		int y=rubbish[i][1];
		int num=0;
		for(int j=0;j<rubbish.length;j++) {//x+1,y+1
			if(rubbish[j][0]==x+1&&rubbish[j][1]==y+1) num++;
			if(rubbish[j][0]==x+1&&rubbish[j][1]==y-1) num++;
			if(rubbish[j][0]==x-1&&rubbish[j][1]==y+1) num++;
			if(rubbish[j][0]==x-1&&rubbish[j][1]==y-1) num++;
		}
		return num;
		
	}

	private static boolean judge(int i, int[][] rubbish) {//判断是否能被选
		// TODO Auto-generated method stub
		int x=rubbish[i][0];
		int y=rubbish[i][1];
		boolean rubbish_four=false;
		out:
		for(int j=0;j<rubbish.length;j++) {//x,j-1
			if(rubbish[j][0]==x&&rubbish[j][1]==y-1) {
				for(int n=0;n<rubbish.length;n++) {//x,j+1
					if(rubbish[n][0]==x&&rubbish[n][1]==y+1) {
						for(int m=0;m<rubbish.length;m++) {//x-1,y
							if(rubbish[m][0]==x-1&&rubbish[m][1]==y) {
								for(int k=0;k<rubbish.length;k++) {//x+1,y
									if(rubbish[k][0]==x+1&&rubbish[k][1]==y) {
										rubbish_four=true;
										break out;
									}
								}
							}
						}
					}
				}
			}
		}
		return rubbish_four;
	}
}



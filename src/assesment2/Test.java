package assesment2;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Journey> journeys = new ArrayList<Journey>();//ʹ�ÿɱ�������չ�Ը���,����Ҫ�޸ĵĶ���̫����........
		journeys.add(new Journey("�ɶ�-����-�Ͼ�",8526,3209,9));
		journeys.add(new Journey("����-տ��-�㽭",6039,2388,12));
		journeys.add(new Journey("��ɽ-����-����",9999,5999,15));
		journeys.add(new Journey("�ع�-�Ϻ�-����",12999,6888,18));
		journeys.add(new Journey("���-����-����",16999,8888,23));
//		Journey[] journeys= new Journey[5];
//		journeys[0] = new Journey("�ɶ�-����-�Ͼ�",8526,3209,9);
//		journeys[1] = new Journey("����-տ��-�㽭",6039,2388,12);
//		journeys[2] = new Journey("��ɽ-����-����",9999,5999,15);
//		journeys[3] = new Journey("�ع�-�Ϻ�-����",12999,6888,18);
//		journeys[4] = new Journey("���-����-����",16999,8888,23);
		Menu menu= new Menu(journeys);
		menu.menuProgram();
	}
}

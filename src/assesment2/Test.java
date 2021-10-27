package assesment2;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Journey> journeys = new ArrayList<Journey>();//使用可变数组扩展性更高,但是要修改的东西太多了........
		journeys.add(new Journey("成都-三亚-南京",8526,3209,9));
		journeys.add(new Journey("广州-湛江-浙江",6039,2388,12));
		journeys.add(new Journey("佛山-丽江-重庆",9999,5999,15));
		journeys.add(new Journey("韶关-上海-西安",12999,6888,18));
		journeys.add(new Journey("天津-杭州-北京",16999,8888,23));
//		Journey[] journeys= new Journey[5];
//		journeys[0] = new Journey("成都-三亚-南京",8526,3209,9);
//		journeys[1] = new Journey("广州-湛江-浙江",6039,2388,12);
//		journeys[2] = new Journey("佛山-丽江-重庆",9999,5999,15);
//		journeys[3] = new Journey("韶关-上海-西安",12999,6888,18);
//		journeys[4] = new Journey("天津-杭州-北京",16999,8888,23);
		Menu menu= new Menu(journeys);
		menu.menuProgram();
	}
}

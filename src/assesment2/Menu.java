package assesment2;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

//菜单
public class Menu {
	private Journey[] journeys;//旅游行程
	//屏幕程序,可变数组
	public Menu(Journey...journeys) {
		super();
		this.journeys = journeys;//传入旅游行程
	}
	public void menuProgram() {
		int fr=0;
		do {
			Scanner select=new Scanner(System.in);
			System.out.println("请选择选项:");
			System.out.println("Add a new passenger to a travel tour (FR-3) \r\n" + 
					" Allow a passenger to make their payment (FR-4) \r\n" + 
					" Display details of a travel tour (FR-5) \r\n" + 
					" Mark a particular travel tour as now departed (FR-6)");
			fr=select.nextInt();//获得选择的数字
			//根据fr选择数字运行相对应功能
			switch (fr) {
			case 3:
				addPassenger();
				break;
			case 4:
				passengerPayment();
				break;
			case 5:
				dispalyTour();
				break;
			case 6:
			
				break;
			}
		} while (fr!=0);
	}
	
	//添加乘客到行程
	//Add a new passenger to a travel tour (FR-3)
	private void addPassenger() {
		int selectJourney;//
		int journeyPrice;//定义旅游价格
		Passengers passengers = new Passengers();
		System.out.println("请输入乘客姓名:");
		Scanner input=new Scanner(System.in);
		passengers.setName(input.next());//输入名字
		System.out.println("请输入"+passengers.getName()+"的年龄:");
		passengers.setAge(input.nextInt());//输入年龄
		System.out.println("请输入"+passengers.getName()+"的饮食要求:");
		passengers.setDiet(input.next());//输入饮食要求
		System.out.println("提供以下行程:");
		for (Journey journey : journeys) {
			System.out.println(journey);
		}
		System.out.println("输入要将"+passengers.getName()+"添加到旅游行程的选择:");
		selectJourney=input.nextInt();//记录选项
		while (selectJourney>journeys.length) {
			System.out.println("该选项不存在!请重新输入!");
			selectJourney=input.nextInt();
		}
		selectJourney=selectJourney-1;//数组从0开始.输入1时对应数组第0项
		
		journeys[selectJourney].setPassengers(passengers);//在对应的旅途中添加旅客,数组从0开始,所以进行--操作,journey类中已标记改乘客为未付款状态
		//根据年龄来确定选择旅游的价格
		if (passengers.getAge()>=18&&passengers.getAge()<=60) {
			journeyPrice=journeys[selectJourney].getAdultPrice();
		}else {
			journeyPrice=journeys[selectJourney].getConcessionPrice();
		}
		System.out.println(passengers.getName()+"已加入:"+journeys[selectJourney].getRoute()+",需要支付"+journeyPrice+"美元来确定此预订");
	}
	//Allow a passenger to make their payment (FR-4)
	/**
	 * 乘客付款 FR4
	 * @param passengers 乘客
	 */
	private void passengerPayment() {
		int selectJourney;//记录选择旅途选项
		int selectPassengers;//记录选择乘客选项
		int journeyPrice;//定义旅游价格
		int confirmFalg;//标记是否确认付款
		Passengers[] passengers=null;//旅途类中存放乘客的数组
		Scanner inputSelect = new Scanner(System.in);
		System.out.println("请选择旅途行程");
		for (int i = 0; i < journeys.length; i++) {
			System.out.println(journeys[i].toString());
		}
		selectJourney=inputSelect.nextInt();
		//判断输入是否合法
		while (selectJourney>journeys.length) {
			System.out.println("该选项不存在!请重新输入!");
			selectJourney=inputSelect.nextInt();
		}
		selectJourney--;
		//
		passengers=journeys[selectJourney].getPassengers();
		System.out.println("请选择乘客");
		//打印选择旅途的所有乘客
		for (Passengers passengers2: passengers) {
			System.out.println(passengers2);
		}
		selectPassengers=inputSelect.nextInt();
		selectPassengers--;//输入1,对应数组第0项
		if (passengers[selectPassengers].getAge()>=18&&passengers[selectPassengers].getAge()<=60) {
			journeyPrice=journeys[selectJourney].getAdultPrice();
		}else {
			journeyPrice=journeys[selectJourney].getConcessionPrice();
		}
		System.out.println("Name: "+passengers[selectPassengers].getName()+" Age:"+passengers[selectPassengers].getAge()+" Price:"+journeyPrice+"元");
		if (journeys[selectJourney].getPassengersPalyFalg(passengers[selectPassengers]).equals("已付款")) {
			System.out.println("用户已付款!");
			return;
		}
		System.out.println("用户是否已付款? 1:确认 2:未确认");
		confirmFalg=inputSelect.nextInt();
		//输入不合法数字时重新输入!
		while (confirmFalg!=1||confirmFalg!=2) {
			if (confirmFalg==1) {
				journeys[selectJourney].setPassengersPalyFalg(passengers[selectPassengers],"已付款");//设为该乘客为付款成功
				System.out.println("确认成功!该乘客已付款!");
				return;
			}else if (confirmFalg==2){
				journeys[selectJourney].setPassengersPalyFalg(passengers[selectPassengers],"未付款");
				System.out.println("确认成功!该乘客未付款!");
				return;
			}else {
				System.out.println("请输入1或2!");//输入不合法数字时提示
			}
		}
	}
	//显示路途信息
	//Allow a passenger to make their payment (FR-5)
	private void dispalyTour() {
		int selectJourney;//
		int journeyPrice;//定义旅游价格
		Scanner inputSelect = new Scanner(System.in);
		System.out.println("提供以下行程:");
		for (Journey journey : journeys) {
			System.out.println(journey);
		}
		selectJourney=inputSelect.nextInt();//记录选项
		while (selectJourney>journeys.length) {
			System.out.println("该选项不存在!请重新输入!");
			selectJourney=inputSelect.nextInt();
		}
		selectJourney=selectJourney-1;//数组从0开始.输入1时对应数组第0项
		//判断旅行是否开始
		Journey journey=journeys[selectJourney];
		if (journeys[selectJourney].getJourneyFlag()) {
			System.out.println("旅游行程:"+journeys[selectJourney].getRoute()+"本次旅行已开始");
		}else {
			System.out.println("旅游行程:"+journeys[selectJourney].getRoute()+"本次旅行尚未开始");
		}
		int Number = 0;//确定的人数
		int noNumber=0;//未确定人数
		System.out.println("有"+""+"个确定的预定和"+"个未确定的预定.以下乘客以确认:");
		HashMap<Passengers, String> hashMap=journeys[selectJourney].getPassengersPalyFalg();
		for (Entry<Passengers, String> passengerEntry : hashMap.entrySet()) {
			if (passengerEntry.getValue().equals("已付款")) {
				System.out.println(passengerEntry.getKey().toString());
				Number++;
			}else {
				noNumber++;
			}
			System.out.println(passengerEntry.toString());
		}
	}
	private boolean departedTour(Journey journey) {
		return false;
		
	}
	
}

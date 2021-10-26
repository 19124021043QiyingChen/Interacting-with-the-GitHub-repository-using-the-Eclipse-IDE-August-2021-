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
		int fr=0;//记录选项
		do {
			Scanner select=new Scanner(System.in);
			System.out.println("请选择选项:");
			System.out.println("FR-3:Add a new passenger to a travel tour ");
			System.out.println("FR-4:Allow a passenger to make their payment");
			System.out.println("FR-5:Display details of a travel tour");
			System.out.println("FR-6:Mark a particular travel tour as now departed");
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
				departedTour();
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
		//判断输入是否合法
		selectJourney=judgeJourneyInputLegal(selectJourney, journeys, input)-1;//数组从0开始.输入1时对应数组第0项
		//第1次判断旅途状态,出发时输出提示
		if (journeys[selectJourney].getJourneyFlag()) {
			System.out.println("该行程已出发!不可添加乘客!");
			return;
		}
		journeys[selectJourney].setPassengers(passengers);//在对应的旅途中添加旅客,数组从0开始,所以进行--操作,journey类中已标记改乘客为未付款状态
		//根据年龄,旅途来确定选择旅游的价格
		journeyPrice=judgePrice(passengers, journeys[selectJourney]);
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
		//调用journey输入函数,判断输入是否合法,并返回合法输入
		selectJourney=judgeJourneyInputLegal(selectJourney, journeys, inputSelect)-1;
		//判断旅途状态并输出提示
		if (journeys[selectJourney].getJourneyFlag()) {
			System.out.println("该行程已出发!不可查看和修改操作!");
			return;
		}
		//获得选中旅途的所有乘客
		passengers=journeys[selectJourney].getPassengers();
		System.out.println("请选择乘客");
		//1:判断是否有乘客,无则输出提示,乘客按顺序添加,判断第一个乘客是否为空即可
//		if (passengers[0]==null) {
//			System.out.println("该旅途中无乘客!");
//			return;
//		}
		//2:调用判断函数判断数组是否非空并输出提示
		if (judgeNull(passengers)) {
			return;
		}
		//打印选择旅途的所有乘客
		for (Passengers passengers2: passengers) {
			if (passengers2!=null) {
				System.out.println(passengers2);
			}
		}
		selectPassengers=inputSelect.nextInt();
		selectPassengers--;//输入1,对应数组第0项
		//传入选择的用户和旅途返回价格
		journeyPrice=judgePrice(passengers[selectPassengers], journeys[selectJourney]);
		if (journeys[selectJourney].getPassengersPalyFalg(passengers[selectPassengers]).equals("已付款")) {
			System.out.println("用户已付款!不可选中进行操作!");
			return;
		}
		System.out.println("Name: "+passengers[selectPassengers].getName()+" Age:"+passengers[selectPassengers].getAge()+" Price:"+journeyPrice+"元");
		System.out.println("用户是否已付款? 1:确认 2:未确认");
		confirmFalg=inputSelect.nextInt();
		//输入不合法数字时重新输入!
		while (confirmFalg!=1||confirmFalg!=2) {
			if (confirmFalg==1) {
				journeys[selectJourney].setPassengersPalyFalg(passengers[selectPassengers],"已付款");//设为该乘客为付款成功
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
		int Number = 0;//确定的人数
		int noNumber=0;//未确定人数
		int totalPrice=0;//总价格
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
		//获取journey中的旅客付款hashmap
		HashMap<Passengers, String> passengersPlayState=journeys[selectJourney].getPassengersPalyFalg();
		//得到确定和未确定人数
		for (Entry<Passengers, String> passengerEntry : passengersPlayState.entrySet()) {
			if (passengerEntry.getValue().equals("已付款")) {
				//记录确定人数
				Number++;
			}else {
				noNumber++;
			}
		}
		System.out.println("有"+Number+"个确定的预定和"+noNumber+"个未确定的预定.以下乘客已确认:");
		//打印确定人数和价格
		for (Entry<Passengers, String> passengerEntry : passengersPlayState.entrySet()) {
			if (passengerEntry.getValue().equals("已付款")) {
				System.out.println(passengerEntry.getKey().toString());
				//传入用户和旅途,确定价格并计算总价格
				totalPrice+=judgePrice(passengerEntry.getKey(),journeys[selectJourney]);
			}
		}
		System.out.println("确认预订的总收据："+totalPrice);
	}
	private void departedTour() {
		Scanner input=new Scanner(System.in);
		int selectJounery=0;//记录选项
		System.out.println("请确定以出发的旅游!");
		for (int i = 0; i < journeys.length; i++) {
			System.out.println(journeys[i].toString());
		}
		selectJounery=input.nextInt();
		//得到选中旅途的乘客付款信息
		HashMap<Passengers, String> passengersPlayState=journeys[--selectJounery].getPassengersPalyFalg();
		//判断是否有用户确定付款,全无时不可设置!
		if (!passengersPlayState.containsValue("已付款")) {
			System.out.println("无用户付款!不可设置为出发状态!");
			return;
		}
		//出发的旅途不可修改和选中操作
		if (journeys[selectJounery].getJourneyFlag()) {
			System.out.println("该旅途已为出发状态!不可修改!");
			return;
		}
		journeys[selectJounery].setJourneyFlag(true);//设为选中的旅途为已出发
		System.out.println("该旅途已设为出发状态!未付款的乘客不可再付款");
		System.out.println("旅途路线:"+journeys[selectJounery].getRoute());
		System.out.println("旅途时间:"+journeys[selectJounery].getDuration()+"天");
		System.out.println("登机乘客如下:");
		outputPassengersPlay(passengersPlayState, "已付款");
		System.out.println("以下乘客未付款,将不包括在登机名单中:");
		outputPassengersPlay(passengersPlayState, "未付款");
	}
	/**
	 * 判断用户年龄,根据旅途价格返回对应价格
	 * @param passengers 用户
	 * @param journey 旅途
	 * @return 价格
	 */
	private int judgePrice(Passengers passengers,Journey journey){
		if (passengers.getAge()>=18&&passengers.getAge()<=60) {
			return journey.getAdultPrice();
		}else {
			return journey.getConcessionPrice();
		}
	}
	/**
	 * 判断乘客数组是否为空
	 * @param passengers 乘客数组
	 * @return 数组为空返回true;非空返回false
	 */
	private boolean judgeNull(Passengers[] passengers) {
		int j=0;
		for (int i = 0; i < passengers.length; i++) {
			if (passengers[i]==null) {
				j++;
			}
			if (j>=passengers.length) {
				System.out.println("该旅途中无乘客!");
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断journey选择的输入的选择是否合法,不合法则重新输入
	 * @param selectJourney journey选项
	 * @param journeys 判断的joureney数组
	 * @param input 键盘输入对象
	 * @return 返回合法输入
	 */
	private int judgeJourneyInputLegal(int selectJourney,Journey[] journeys,Scanner input) {
		while (selectJourney>journeys.length||selectJourney<=0) {
			System.out.println("该选项不存在!请重新输入!");
			selectJourney=input.nextInt();
		}
		return selectJourney;
	}
	/**
	 * 根据输入字符串,打印出符合的乘客信息
	 * @param passengersPlayState 记录付款信息的hashmap
	 * @param string 条件字符串
	 */
	private void outputPassengersPlay(HashMap<Passengers, String> passengersPlayState,String string) {
		for (Entry<Passengers, String> passengerEntry : passengersPlayState.entrySet()) {
			if (passengerEntry.getValue().equals(string)) {
				System.out.println(passengerEntry.getKey().toString());
			}
		}
	}
}

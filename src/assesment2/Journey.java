package assesment2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Journey {
	private String Route;//旅途途经地点
	private int adultPrice;//成人票
	private int concessionPrice;//优惠票
	private int duration;//旅行时间
	private int passengersNumber=0;//定义旅行人数,最大为20人
	private boolean journeyFlag=false;//定义旅游状态,未出发为false,出发为true
	private Passengers[] passengers=new Passengers[20];//存放旅客的数组
	private Map<Passengers,String> passengersPalyFalg=new HashMap<Passengers, String>();//旅客付款标记,付款则标记为true,未付款标记为false;一个旅客对一个标记
	public Journey() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Journey(String route, int adultPrice, int concessionPrice, int duration) {
		super();
		Route = route;
		this.adultPrice = adultPrice;
		this.concessionPrice = concessionPrice;
		this.duration = duration;
	}

	public String getRoute() {
		return Route;
	}
	public void setRoute(String route) {
		Route = route;
	}
	public int getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(int adultPrice) {
		this.adultPrice = adultPrice;
	}
	public int getConcessionPrice() {
		return concessionPrice;
	}
	public void setConcessionPrice(int concessionPrice) {
		this.concessionPrice = concessionPrice;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getPassengersNumber() {
		return passengersNumber;
	}
	public void setPassengersNumber(int passengersNumber) {
		this.passengersNumber = passengersNumber;
	}
	public Passengers[] getPassengers() {
		return passengers;
	}
	public void setPassengers(Passengers[] passengers) {
		this.passengers = passengers;
	}
	
	public HashMap<Passengers, String> getPassengersPalyFalg() {
		return (HashMap<Passengers, String>) passengersPalyFalg;
	}
	public boolean getJourneyFlag() {
		return journeyFlag;
	}
	public void setJourneyFlag(boolean journeyFlag) {
		this.journeyFlag = journeyFlag;
	}
	/**
	 * 设置乘客付费状态
	 * @param passengers 乘客
	 * @return 付款状态键值对
	 */
	public String setPassengersPalyFalg(Passengers passengers,String paly) {
		if (journeyFlag) {
			System.out.println("该旅途已出发!不可修改付款状态!");
			return passengersPalyFalg.put(passengers, "未付款");
		}else if(paly.equals("未付款")){
			System.out.println("该旅途已出发!不可付款");
			return passengersPalyFalg.put(passengers, "未付款");
		}
		System.out.println("确认成功!该乘客已付款!");
		return passengersPalyFalg.put(passengers, paly);
		
	}
	/**
	 * 传入用户对象,得到用户付款信息
	 * @param passengers 用户
	 * @return 返回用户付款信息
	 */
	public String getPassengersPalyFalg(Passengers passengers) {
		return passengersPalyFalg.get(passengers);
	}
	/**
	 * 添加用户到旅途中,并设为未付款状态,当人数大于20时或旅途出发时不可在添加乘客
	 * @param passengers 需要添加到旅途的乘客
	 */
	public void setPassengers(Passengers passengers) {
		//判断人数.大于20时不可添加
		if (passengersNumber>19) {
			System.out.println("该旅途已满20人!请选择其他旅途!");
			return;
		}else if (journeyFlag) {
			System.out.println("该旅途已出发!不可添加乘客!");
			return;
		}
		this.passengers[passengersNumber]= passengers;//第一个放在数组第0位,passengersNumber为人数,对应位置信息
		passengersPalyFalg.put(this.passengers[passengersNumber], "未付款");//进行未付款标记
		passengersNumber++;//人数加1
	}
	@Override
	public String toString() {
		return "Journey [Route=" + Route + ", adultPrice=" + adultPrice + ", concessionPrice=" + concessionPrice
				+ ", duration=" + duration + ", passengersNumber=" + passengersNumber + ", passengers="
				+ Arrays.toString(passengers) + ", passengersPalyFalg=" + passengersPalyFalg + "]";
	}
	
}

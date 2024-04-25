package sr.thrift.server;

import org.apache.thrift.TException;
import sr.rpc.thrift.Calculator;

import java.util.List;

public class CalculatorHandler implements Calculator.Iface {

	int id;

	public CalculatorHandler(int id) {
		this.id = id;
	}

	@Override
	public int add(int n1, int n2) {
		System.out.println("CalcHandler#" + id + " add(" + n1 + "," + n2 + ")");
		if(n1 > 1000 || n2 > 1000) { 
			try { Thread.sleep(6000); } catch(java.lang.InterruptedException ex) { }
			System.out.println("DONE");
		}
		return n1 + n2;
	}

	@Override
	public int subtract(int num1, int num2) throws TException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double avg(List<Long> val) throws TException {
		if(val.isEmpty()) {
			throw new TException("no data");
		}
		double res = 0;
		for (Long d : val) res += d;
		return res/val.size();
	}

}


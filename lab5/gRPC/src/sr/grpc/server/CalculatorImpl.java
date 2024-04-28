package sr.grpc.server;

import sr.grpc.gen.ArithmeticOpResult;
import sr.grpc.gen.CalculatorGrpc.CalculatorImplBase;

import java.util.List;

public class CalculatorImpl extends CalculatorImplBase 
{
	@Override
	public void add(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("addRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() + request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		if(request.getArg1() > 100 && request.getArg2() > 100) try { Thread.sleep(5000); } catch(java.lang.InterruptedException ex) { }
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

	@Override
	public void subtract(sr.grpc.gen.ArithmeticOpArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver) 
	{
		System.out.println("subtractRequest (" + request.getArg1() + ", " + request.getArg2() +")");
		int val = request.getArg1() - request.getArg2();
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}

	@Override
	public void multiply(sr.grpc.gen.ArithmeticMulArguments request,
			io.grpc.stub.StreamObserver<sr.grpc.gen.ArithmeticOpResult> responseObserver)
	{
		List<Integer> arr = request.getArgList();

		System.out.println("multiplyRequest (" + arr + ")");
		int val = 1;
		for (int i = 0; i < arr.size(); i++) val *= arr.get(i);
		ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
		responseObserver.onNext(result);
		responseObserver.onCompleted();
	}


}

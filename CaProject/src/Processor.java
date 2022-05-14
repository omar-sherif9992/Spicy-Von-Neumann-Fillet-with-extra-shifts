
public class Processor {
	private Register[] gprs;
	private final Register zeroRegister;
	private Register programCounter;
	private ALU alu;

	public Processor() {
		gprs = new Register [32]; // I've put zeroRegister in it
		zeroRegister = new Register(0,"R0");
		gprs[0]=zeroRegister;
		for(int i =1;i<gprs.length;i++)
			gprs[i]= new Register(0,"R"+i);

		programCounter = new Register(0,"PC");
		alu = new ALU(this);
	}

	public void run(DataPath dataPath) {
		while(programCounter.getData()<Memory.numberOfTotalInstructions){
		dataPath.fetch();
		dataPath.decode();
		dataPath.execute();
		dataPath.mem();
		dataPath.writeBack();
		System.out.println("---------------------------------------");
	}
	}
	public Register[] getGprs() {
		return gprs;
	}
	public void setGprs(Register[] gprs) {
		this.gprs = gprs;
	}
	public Register getZeroRegister() {
		return zeroRegister;
	}

	public Register getProgramCounter() {
		return programCounter;
	}
	
	public void setProgramCounter(Register programCounter) {
		this.programCounter = programCounter;
	}
	public ALU getAlu() {
		return alu;
	}
	public void setAlu(ALU alu) {
		this.alu = alu;
	}
}

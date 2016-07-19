package lab7.executor;

import interfaces.task7.executor.SumTask;

import java.math.BigInteger;
import java.util.Random;

public class SumTaskImpl extends AbstractTask implements SumTask{

    Random random = new Random();
    private BigInteger result = BigInteger.ZERO;
    private int iterationCount;
    private long maxNumber;


    @Override
    public void setCount(int i) {
        this.iterationCount = i;
    }

    @Override
    public void setMax(long l) {
        if (l < 1) throw new IllegalArgumentException("Max value must be at least one");
        this.maxNumber = l;
    }

    @Override
    public BigInteger getRandom() {
            long bits, val;
            do {
                bits = (this.random.nextLong() << 1) >>> 1;
                val = bits % (maxNumber + 1);
            } while (bits-val+(maxNumber) < 0L);
        return BigInteger.valueOf(val);
    }

    @Override
    public BigInteger getResult() {
        return result;
    }

    @Override
    public boolean execute() throws Exception {

        try {
            for (int i = 0; i < iterationCount; i++){
                this.result = this.result.add(this.getRandom());
            }
        } catch (Exception e){
            return false;
        }

        return true;
    }
}

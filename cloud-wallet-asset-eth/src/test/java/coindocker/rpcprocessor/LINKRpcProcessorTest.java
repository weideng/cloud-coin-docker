package coindocker.rpcprocessor;

import coindocker.AbstractTestCase;
import com.ourdax.coindocker.asset.link.LINKRpcProcessor;
import com.ourdax.coindocker.block.Block;
import com.ourdax.coindocker.block.BlockTrans;
import com.ourdax.coindocker.block.SimpleBlock;
import com.ourdax.coindocker.exception.BizException;
import com.ourdax.coindocker.rpc.RpcTransRequest;
import com.ourdax.coindocker.rpc.RpcTransResponse;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangjinyang on 2018/1/16.
 */
public class LINKRpcProcessorTest extends AbstractTestCase{

  @Autowired
  private LINKRpcProcessor processor;


  @Test
  public void testQueryLatestBlock() {
    Block latestBlock = processor.getLatestBlock();
    System.out.println(latestBlock);
  }

  @Test
  public void testQueryTrans() {
    Block block = new SimpleBlock("4974996", null);
    BlockTrans blockTrans = processor.queryTrans(block);
    System.out.println(blockTrans);
  }

  @Test
  public void testGetConfirmationNum() {
    Block latestBlock = processor.getLatestBlock();
    Block block = new SimpleBlock("4973524", null);
    Assert.assertEquals(Integer.parseInt(latestBlock.getBlockNumber()) - 4973524, processor.getConfirmationNum(block));

  }

  @Test
  public void testFindRecentTrans() {
    Block latestBlock = processor.getLatestBlock();
    int start = Integer.parseInt(latestBlock.getBlockNumber());
    int count = 0;
    for (;;) {
      Block block = new SimpleBlock(String.valueOf(start), null);
      BlockTrans blockTrans = processor.queryTrans(block);
      if (blockTrans.getTrans().size() > 0) {
        System.out.println(blockTrans);
        count++;
        if (count > 10) {
          break;
        }
      }
      start--;
    }
  }

  @Test
  public void testQueryTransByTxId() {
    String txId = "0x72712623c683e4896d1caef3cc370a3678425f1308332092e256738d6e29b252";
    System.out.println(processor.queryTransInfo(txId));
  }

  @Test
  public void testQueryTransSince() {
    Block block = new SimpleBlock("4973523", null);
    System.out.println(processor.queryTransSince(block));
  }

  @Test
  public void testQueryBalance() {
    BigDecimal balance = processor.queryBalance();
    System.out.println(balance);
  }

  @Test
  public void testQueryContractBalance() {
    BigDecimal contractBalance = processor.queryBalance();
    System.out.println(contractBalance);
  }

  @Test
  public void testTransferOut() throws BizException {
    RpcTransRequest request = new RpcTransRequest();
    request.setTo("0x8c1e6839af0b626d941b203def465f1f5224294f");
    request.setAmount(new BigDecimal("1.07"));
    RpcTransResponse response = processor.defaultTransfer(request);
    System.out.println(response);
  }

}

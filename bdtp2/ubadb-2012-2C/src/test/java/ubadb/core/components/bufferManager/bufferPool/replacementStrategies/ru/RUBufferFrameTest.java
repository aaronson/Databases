package ubadb.core.components.bufferManager.bufferPool.replacementStrategies.ru;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ubadb.core.exceptions.BufferFrameException;
import ubadb.core.testDoubles.DummyObjectFactory;
import ubadb.core.util.TestUtil;

public class RUBufferFrameTest {

	@Test
	public void testLastUsageAfterCreation() throws InterruptedException{
		RUBufferFrame bufferFrame0 = new RUBufferFrame(DummyObjectFactory.PAGE);
		Thread.sleep(TestUtil.PAUSE_INTERVAL); //Sleep to guarantee that the second frame is created some time after the first one
		RUBufferFrame bufferFrame1 = new RUBufferFrame(DummyObjectFactory.PAGE);
		
		assertTrue(bufferFrame0.getLastUsage().before(bufferFrame1.getLastUsage()));
	}
	
	@Test
	public void testLastUsageAfterPinning() throws InterruptedException{
		RUBufferFrame bufferFrame0 = new RUBufferFrame(DummyObjectFactory.PAGE);
		RUBufferFrame bufferFrame1 = new RUBufferFrame(DummyObjectFactory.PAGE);
		
		bufferFrame0.pin();
		Thread.sleep(TestUtil.PAUSE_INTERVAL); //Sleep to guarantee that the second frame is created some time after the first one
		bufferFrame1.pin();
		
		assertTrue(bufferFrame0.getLastUsage().before(bufferFrame1.getLastUsage()));
	}
		
	@Test
	public void testLastUsageAfterUnpinning() throws InterruptedException, BufferFrameException{
		RUBufferFrame bufferFrame0 = new RUBufferFrame(DummyObjectFactory.PAGE);
		RUBufferFrame bufferFrame1 = new RUBufferFrame(DummyObjectFactory.PAGE);
		
		bufferFrame0.pin();
		bufferFrame1.pin();
		
		bufferFrame0.unpin();
		Thread.sleep(TestUtil.PAUSE_INTERVAL); //Sleep to guarantee that the second frame is created some time after the first one
		bufferFrame1.unpin();
		
		assertTrue(bufferFrame0.getLastUsage().before(bufferFrame1.getLastUsage()));
	}
}

package ubadb.core.components.bufferManager.bufferPool.replacementStrategies.ru.touchCount;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ubadb.core.components.bufferManager.bufferPool.BufferFrame;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.touchCount.TouchCountBufferFrame;
import ubadb.core.components.bufferManager.bufferPool.replacementStrategies.touchCount.TouchCountReplacementStrategy;
import ubadb.core.exceptions.BufferFrameException;
import ubadb.core.exceptions.PageReplacementStrategyException;
import ubadb.core.testDoubles.DummyObjectFactory;
import ubadb.core.util.TestUtil;

public class TCReplacementStrategyTest {

	private TouchCountReplacementStrategy strategy;

	@Before
	public void setUp() {
		strategy = new TouchCountReplacementStrategy();
	}

	@Test(expected = PageReplacementStrategyException.class)
	public void testNoPageToReplace() throws Exception {
		BufferFrame frame0 = strategy.createNewFrame(DummyObjectFactory.PAGE);
		BufferFrame frame1 = strategy.createNewFrame(DummyObjectFactory.PAGE);

		frame0.pin();
		frame1.pin();

		strategy.findVictim(Arrays.asList(frame0, frame1));
	}

	@Test
	public void testOnlyOneToReplace() throws Exception {
		BufferFrame frame0 = strategy.createNewFrame(DummyObjectFactory.PAGE);
		BufferFrame frame1 = strategy.createNewFrame(DummyObjectFactory.PAGE);
		BufferFrame frame2 = strategy.createNewFrame(DummyObjectFactory.PAGE);

		frame0.pin();
		frame1.pin();

		assertEquals(frame2,
				strategy.findVictim(Arrays.asList(frame0, frame1, frame2)));
	}

	@Test
	public void testOneVeryHot() throws PageReplacementStrategyException {
		BufferFrame frame0 = strategy.createNewFrame(DummyObjectFactory.PAGE);
		BufferFrame frame1 = strategy.createNewFrame(DummyObjectFactory.PAGE);

		pinUnpin(frame0);
		pinUnpin(frame0);
		pinUnpin(frame0);
		pinUnpin(frame0);

		assertEquals(frame1, strategy.findVictim(new ArrayList<BufferFrame>()));
	}

	@Test
	public void testTwoVeryHot() throws PageReplacementStrategyException {
		BufferFrame frame0 = strategy.createNewFrame(DummyObjectFactory.PAGE);
		BufferFrame frame1 = strategy.createNewFrame(DummyObjectFactory.PAGE);

		pinUnpin(frame0);
		pinUnpin(frame0);
		pinUnpin(frame0);
		pinUnpin(frame0);
		pinUnpin(frame1);
		pinUnpin(frame1);
		pinUnpin(frame1);
		pinUnpin(frame1);
		pinUnpin(frame1);

		assertEquals(frame0, strategy.findVictim(new ArrayList<BufferFrame>()));
	}

	@Test
	public void testHotMovedToColdThenEvicted()
			throws PageReplacementStrategyException {
		BufferFrame frame0 = strategy.createNewFrame(DummyObjectFactory.PAGE);
		BufferFrame frame1 = strategy.createNewFrame(DummyObjectFactory.PAGE);

		pinUnpin(frame1);
		pinUnpin(frame1);
		pinUnpin(frame1);
		pinUnpin(frame1);

		BufferFrame frame2 = strategy.createNewFrame(DummyObjectFactory.PAGE);

		pinUnpin(frame2);
		pinUnpin(frame2);
		pinUnpin(frame0);
		pinUnpin(frame0);

		assertEquals(frame1, strategy.findVictim(new ArrayList<BufferFrame>()));
	}

	private void pinUnpin(BufferFrame frame) {
		frame.pin();
		try {
			frame.unpin();
		} catch (BufferFrameException e) {
			fail();
		}

	}
}

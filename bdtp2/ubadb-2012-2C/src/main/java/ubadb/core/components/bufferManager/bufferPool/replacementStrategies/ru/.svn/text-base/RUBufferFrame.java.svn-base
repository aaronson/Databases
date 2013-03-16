package ubadb.core.components.bufferManager.bufferPool.replacementStrategies.ru;

import java.util.Date;

import ubadb.core.common.Page;
import ubadb.core.components.bufferManager.bufferPool.BufferFrame;
import ubadb.core.exceptions.BufferFrameException;

/**
 * This frame updates adds a last usage date to the base BufferFrame class,
 * which is updated every time it's created, pinned or unpinned
 * 
 */
public class RUBufferFrame extends BufferFrame {

	Date lastUsage;

	public RUBufferFrame(Page page) {
		super(page);
		updateLastUsage();
	}

	@Override
	public void pin() {
		super.pin();
		updateLastUsage();
	}

	@Override
	public void unpin() throws BufferFrameException {
		super.unpin();
		updateLastUsage();
	}

	private void updateLastUsage() {
		lastUsage = new Date();
	}

	public Date getLastUsage() {
		return lastUsage;
	}
}

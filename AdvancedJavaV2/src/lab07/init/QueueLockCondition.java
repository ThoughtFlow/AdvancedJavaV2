package lab07.init;

@SuppressWarnings("unused")
public class QueueLockCondition implements SynchronizedQueue {

	private Element first, last;
	private int curSize, maxSize;

	public QueueLockCondition(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public synchronized void put(Object o) throws InterruptedException {
		// Implement this using read write locks

	}

	@Override
	public synchronized Object get() throws InterruptedException {
		// Implement this using read write locks
		return null;
	}
	
	private static class Element {
		final Object value;
		Element next;


		Element(Object value) {
			this.value = value;
		}
	}
}

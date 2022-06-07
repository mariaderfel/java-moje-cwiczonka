package pl.kurswspolbieznosci.tydzien7;

import com.jayway.awaitility.Awaitility;
import net.jodah.concurrentunit.Waiter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class AsyncTestingLesson {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTestingLesson.class);

    static class AsyncCalculation {
        private volatile int number = 0;
        private final Executor executor;

        public AsyncCalculation(Executor executor) {
            this.executor = executor;
        }

        public void calculate() {
            executor.execute(() -> {
                try {
                    Random random = new Random();
                    Thread.sleep(1_000);
                    number = random.nextInt(100) + 1;
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            });
        }
    }

    @Test
    public void shouldUpdateANumber() throws InterruptedException {
        AsyncCalculation calculation = new AsyncCalculation(Executors.newSingleThreadExecutor());
        calculation.calculate();
        Thread.sleep(2_000);
        Assert.assertThat(calculation.number, greaterThan(0));
    }

    @Test
    public void shouldUpdateANumberAwait() throws InterruptedException {
        AsyncCalculation calculation = new AsyncCalculation(Executors.newSingleThreadExecutor());
        calculation.calculate();
        Awaitility.await().atMost(2_000, TimeUnit.MILLISECONDS).until(() -> {
            Assert.assertThat(calculation.number, greaterThan(0));
        });
    }

    static class MessageBus {
        private final List<Consumer<String>> listeners = new ArrayList<>();
        private final Executor executor;

        public MessageBus(Executor executor) {
            this.executor = executor;
        }

        public void send(String message) {
            executor.execute(() -> {
                listeners.forEach(c -> {
                    try {
                        Thread.sleep(500);
                        c.accept(message);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                });
            });
        }

        public void register(Consumer<String> consumer) {
            listeners.add(consumer);
        }
    }

    @Test
    public void shouldGetAMessage() throws TimeoutException {
        Waiter waiter = new Waiter();
        MessageBus bus = new MessageBus(Executors.newSingleThreadExecutor());
        bus.register(m -> {
            waiter.assertEquals("secret", m);
            waiter.resume();
        });
        bus.send("secret");
        waiter.await(1_000, TimeUnit.MILLISECONDS);
    }
}

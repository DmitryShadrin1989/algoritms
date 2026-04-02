# Система лимитов платежей (General coding, livecoding)

Задача на **написание кода в IDE** кандидата. Расчёт: **~60 минут**.

**Базовая часть** + **усложнение** (асинхронный доступ к истории, кеш лимитов).

- **15 грейд:** база.
- **16 грейд:** усложнение.

Документация секции: **General coding Java (экс livecoding / экспериментальная)**; в начале — **#rekomendaciipoprovedeniju**.

---

## Постановка (основная часть)

Текст одинаковый для Java и C# (ниже — скелеты).

```text
Вы — backend-разработчик в финтех компании.
Компания предоставляет платежные услуги и должна контролировать лимиты пользователей.
Product owner просит создать систему проверки лимитов перед проведением платежей.

## Определения

Платеж:
- id пользователя
- сумма (в рублях с копейками)
- тип операции (только списание)
- время операции

Лимиты пользователя:
- суточный лимит по сумме (за 24 часа)
- максимальный размер одной операции

История операций:
- список совершенных платежей пользователя

## Важно
Настройки лимитов пользователей и история платежей предоставляются другими компонентами системы.
Вам необходимо спроектировать контракты для получения этих данных.
Реализацию этих компонентов делать не нужно.

## Задача
Написать систему проверки лимитов, которая:
- на вход получает платеж
- проверяет лимиты
- возвращает результат проверки: можно ли провести операцию
- если нельзя, то указывает причину (какой именно лимит будет превышен)

Проведение платежа не входит в вашу задачу - другая команда займется обработкой платежей после проверки.
Ваша задача - только проверка возможности проведения платежа.

## Ограничения
В рамках данной задачи считаем, что все платежи одного пользователя происходят строго последовательно.
Во время проверки лимита не может быть проведен платеж того же пользователя.
```

### Скелет: Java

```java
class PaymentsChecker {
    PaymentsHistoryService paymentsHistoryService;
    UserLimitsService userLimitsService;

    public ??? checkPayment(??? payment) {
        // TODO implement
    }
}

interface PaymentsHistoryService {
    // TODO any functions
}

interface UserLimitsService {
    // TODO any functions
}
```

### Скелет: C#

```csharp
class PaymentsChecker
{
    IPaymentsHistoryService _paymentsHistoryService;
    IUserLimitsService _userLimitsService;

    public ??? CheckPayment(??? payment)
    {
        // TODO: implement
    }
}

interface IPaymentsHistoryService
{
    // TODO: any functions
}

interface IUserLimitsService
{
    // TODO: any functions
}
```

---

## FAQ (вопросы кандидата)

| Вопрос | Ответ |
|--------|--------|
| Что считать «сутками»? | **Последние 24 часа** от **момента (времени) проверяемого платежа** (или от `now` — согласовать). |
| Часовой пояс? | Можно **не учитывать**, например везде **UTC**. |
| Включать ли **текущий** платеж в суточный лимит? | **Да** — проверять **сумму истории + новый платёж** против `dailyLimit`. |
| Нет настроек лимитов? | По умолчанию **запрещаем** операцию (с явной причиной). |
| Точность сумм? | **Рубли с копейками** — см. блок «Работа с деньгами». |
| Реализация сервисов? | **Только API** (интерфейсы). |
| Конкурентные платежи? | **Не моделируем** — последовательность строгая. |

После достаточного кода: попросить **скопировать код + тесты** в интервьюшницу.

---

## На что смотреть (интервьюер)

- Тип для денег: **`BigDecimal`** (Java) / **`decimal`** (C#) / **long в копейках** — ок; **`double`** / **`float`** — **штраф за проектирование** (см. ниже).
- Учитывается ли **новый платёж** в суточном лимите.
- Корректен ли интервал **24 часа** (или делегирован сервису истории, например `getPaymentsSince(userId, since)`).
- **Не** тянуть **всю** историю — запрос **за окно** (эффективность API).
- Пограничные случаи: **ровно** на лимите; **нулевые** и **отрицательные** лимиты / суммы.

---

## Усложнение

```text
История операций и настройки хранятся во внешней системе.
Доступ к истории асинхронный и может быть медленным.
Настройки меняются редко. Аудитория 10М пользователей.

Нужно адаптировать код к этим условиям.
```

### Подсказки интервьюеру

- История недоступна → **только проверка max операции** **или** **запрет** с отдельной причиной — за кандидатом, но обсудить.
- **Кеш** настроек — хорошая практика (в условии: настройки редко меняются).
- Таймаут ожидания истории — **1–3 с** разумно; решает кандидат.
- Асинхронный ли публичный метод — решает кандидат.
- Исключения в **async**-цепочках обработать явно.

**Когда выдавать:** сразу с базой, если тянет; иначе отдельно (**3–5 мин** не думает / код раздувается).

---

## TLDR по грейдам

| Итог | Условие |
|------|---------|
| **NH** | Не решил; много помощи; вмешательство в код; качество \< 3; **два нуля** в tear-off (без усложнения). |
| **14** | Минимальное решение + качество **≥ 3**. |
| **15** | Минимальное решение + качество **≥ 4**, нет **нулей** в tear-off (кроме усложнения). |
| **16** | **Усложнение** + качество **≥ 4**. |

Методика: **#tear-off** в той же внутренней доке.

---

## Минимальное решение (acceptance)

1. Сумма по истории за **24 часа** (окно относительно времени платежа / согласованного «сейчас»).
2. `amount > maxOperationAmount` → **отказ** с **явной причиной**.
3. Сумма за сутки **с учётом истории** превышает `dailyLimit` → **отказ**.
4. **Новый платёж входит** в расчёт дневного лимита (не только «история как была»).
5. Возвращается **конкретная причина** / тип нарушения.

### Усложнение (что проверять)

- **CompletableFuture** / Task / аналог — корректно.
- Лимиты **кешируются** на несколько минут (намёк в постановке).
- Поведение при **отказе API истории** явно выбрано и реализовано.

---

## Работа с деньгами

За **`double`** / **`int`** без осознанного обоснования — **−0.5** к проектированию. Сначала **спросить**, нет ли проблем; если кандидат **исправляет** — штраф **не** вешать.

---

## Tear-off (шаблон оценки)

### Придумывание решения — 0 / 0.5 / 1

Самостоятельность, API, расширяемость, тестируемость, структуры данных, асимптотика, corner cases.

- **0:** никакой самостоятельности; заметная помощь; неподходящие типы и не понимает после наводок.
- **0.5:** мелкие недочёты; рабочее, но неоптимально.
- **1:** без критичных проблем; оптимально или осознанно обосновано.

### Реализация — 0 / 1 / 2

Нейминг, читаемость, язык, оптимальность, валидация, ошибки API, **null**.

### Ошибки и их поиск — NH / 0 / 1 / 2

Как в общей методике (подсказки сценариев, дебаг).

### Тестирование — 0 / 0.5 / 1

Только с **assert**; `main` + assert допустим; **принты без проверок** — не засчитывать.

### Усложнение

**1** балл, если сделано без оставшихся багов.

---

## Примеры тестов: основная часть

Кандидат **не успеет всё**.

**Валидация:** `null` / отрицательные суммы; `userId`; нет лимитов; ошибки сервисов.

**Happy path:** проходит; превышен max операции; превышен дневной (с историей).

**Дополнительно:** пустая история; **ровно** на границе; пользователь без лимитов; некорректный ввод.

## Примеры тестов: усложнение

Успешный async-путь; таймаут истории; **частичная** проверка при недоступности истории.

---

## Эталонный код (Java, упрощённый вариант)

Обычно кандидаты пишут проще. Ниже — ориентир «близко к максимуму».

```java
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public record Payment(
        @NonNull BigDecimal amount,
        @NonNull Instant timestamp
) {
    public Payment {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}

public interface PaymentHistoryRepository {
    List<Payment> getPaymentsSince(String userId, Instant since);
}

public record UserLimits(
        @NonNull String userId,
        @NonNull BigDecimal dailyLimit,
        @NonNull BigDecimal maxOperationAmount
) {
    public UserLimits {
        if (dailyLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Daily limit cannot be negative");
        }
        if (maxOperationAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Max operation amount cannot be negative");
        }
    }
}

public interface UserLimitsRepository {
    Optional<UserLimits> getUserLimits(String userId);
}

public enum LimitViolationType {
    DAILY_LIMIT_EXCEEDED,
    MAX_OPERATION_AMOUNT_EXCEEDED,
    USER_LIMITS_NOT_FOUND
}

public record LimitCheckResult(
        boolean isAllowed,
        LimitViolationType violationType,
        String reason
) {
    public static LimitCheckResult allowed() {
        return new LimitCheckResult(true, null, null);
    }

    public static LimitCheckResult denied(LimitViolationType violationType, String reason) {
        return new LimitCheckResult(false, violationType, reason);
    }
}

@RequiredArgsConstructor
public class PaymentLimitService {

    @NonNull
    private final UserLimitsRepository userLimitsRepository;
    @NonNull
    private final PaymentHistoryRepository paymentHistoryRepository;

    public LimitCheckResult checkLimits(@NonNull Payment payment, @NonNull String userId) {

        Optional<UserLimits> limitsOpt = userLimitsRepository.getUserLimits(userId);
        if (limitsOpt.isEmpty()) {
            return LimitCheckResult.denied(
                    LimitViolationType.USER_LIMITS_NOT_FOUND,
                    "No limits configured for user: " + userId
            );
        }

        UserLimits limits = limitsOpt.get();

        LimitCheckResult operationLimitResult = checkMaxOperationAmount(payment, limits);
        if (!operationLimitResult.isAllowed()) {
            return operationLimitResult;
        }

        LimitCheckResult dailyLimitResult = checkDailyLimit(payment, userId, limits);
        if (!dailyLimitResult.isAllowed()) {
            return dailyLimitResult;
        }

        return LimitCheckResult.allowed();
    }

    private LimitCheckResult checkMaxOperationAmount(Payment payment, UserLimits limits) {
        if (payment.amount().compareTo(limits.maxOperationAmount()) > 0) {
            return LimitCheckResult.denied(
                    LimitViolationType.MAX_OPERATION_AMOUNT_EXCEEDED,
                    String.format("Payment amount %.2f exceeds maximum operation limit %.2f",
                            payment.amount(), limits.maxOperationAmount())
            );
        }
        return LimitCheckResult.allowed();
    }

    private LimitCheckResult checkDailyLimit(Payment payment, String userId, UserLimits limits) {
        Instant dayAgo = payment.timestamp().minus(Duration.ofDays(1));
        List<Payment> recentPayments = paymentHistoryRepository.getPaymentsSince(userId, dayAgo);

        BigDecimal dailySpent = calculateTotalAmount(recentPayments);
        BigDecimal totalWithNewPayment = dailySpent.add(payment.amount());

        if (totalWithNewPayment.compareTo(limits.dailyLimit()) > 0) {
            return LimitCheckResult.denied(
                    LimitViolationType.DAILY_LIMIT_EXCEEDED,
                    String.format("Daily limit exceeded. Spent: %.2f, Limit: %.2f, Attempted: %.2f",
                            dailySpent, limits.dailyLimit(), payment.amount())
            );
        }

        return LimitCheckResult.allowed();
    }

    private BigDecimal calculateTotalAmount(List<Payment> payments) {
        return payments.stream()
                .map(Payment::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
```

**Порядок проверок:** сначала **max операции** (можно **не** дергать историю при превышении).

**Сравнение с лимитом:** в эталоне отказ при `>`; **ровно лимит** — проходит. Уточнить с кандидатом «строго больше» vs «≥».

---

## Эталонные тесты (JUnit 5 + Mockito)

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentLimitServiceTest {

    @Mock
    private UserLimitsRepository userLimitsRepository;

    @Mock
    private PaymentHistoryRepository paymentHistoryRepository;

    private PaymentLimitService paymentLimitService;

    private UserLimits defaultLimits;
    private Instant now;

    @BeforeEach
    void setUp() {
        paymentLimitService = new PaymentLimitService(userLimitsRepository, paymentHistoryRepository);
        defaultLimits = new UserLimits("user1", new BigDecimal("10000.00"), new BigDecimal("5000.00"));
        now = Instant.now();
    }

    @Test
    void shouldAllowPaymentWhenAllLimitsAreMet() {
        var payment = new Payment(new BigDecimal("1000.00"), now);
        var userId = "user1";

        when(userLimitsRepository.getUserLimits(userId))
                .thenReturn(Optional.of(defaultLimits));
        when(paymentHistoryRepository.getPaymentsSince(userId, now.minus(Duration.ofDays(1))))
                .thenReturn(Collections.emptyList());

        var result = paymentLimitService.checkLimits(payment, userId);

        assertTrue(result.isAllowed());
        assertNull(result.violationType());
        assertNull(result.reason());
    }

    @Test
    void shouldDenyPaymentWhenMaxOperationAmountExceeded() {
        var payment = new Payment(new BigDecimal("6000.00"), now);
        var userId = "user1";

        when(userLimitsRepository.getUserLimits(userId))
                .thenReturn(Optional.of(defaultLimits));

        var result = paymentLimitService.checkLimits(payment, userId);

        assertFalse(result.isAllowed());
        assertEquals(LimitViolationType.MAX_OPERATION_AMOUNT_EXCEEDED, result.violationType());
        assertTrue(result.reason().contains("exceeds maximum operation limit"));
    }

    @Test
    void shouldDenyPaymentWhenDailyLimitExceeded() {
        var payment = new Payment(new BigDecimal("2000.00"), now);
        var userId = "user1";

        var existingPayments = List.of(
                new Payment(new BigDecimal("4000.00"), now.minus(Duration.ofHours(12))),
                new Payment(new BigDecimal("5000.00"), now.minus(Duration.ofHours(18)))
        );

        when(userLimitsRepository.getUserLimits(userId))
                .thenReturn(Optional.of(defaultLimits));
        when(paymentHistoryRepository.getPaymentsSince(userId, now.minus(Duration.ofDays(1))))
                .thenReturn(existingPayments);

        var result = paymentLimitService.checkLimits(payment, userId);

        assertFalse(result.isAllowed());
        assertEquals(LimitViolationType.DAILY_LIMIT_EXCEEDED, result.violationType());
        assertTrue(result.reason().contains("Daily limit exceeded"));
    }

    // TODO: shouldAllowPaymentWhenExactlyAtDailyLimit
    // TODO: shouldAllowPaymentWhenExactlyAtMaxOperationAmount
    // TODO: shouldDenyPaymentWhenUserLimitsNotFound
    // TODO: shouldAllowPaymentWithEmptyPaymentHistory
    // TODO: shouldAllowPaymentWithMultiplePaymentsWithinLimits
    // TODO: shouldIgnorePaymentsOlderThan24Hours (через мок репозитория)
    // TODO: shouldHandleZeroLimits
    // TODO: невалидные входные данные
    // TODO: временные зоны (если выйдет время)
}
```

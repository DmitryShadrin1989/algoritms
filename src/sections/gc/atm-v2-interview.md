# Банкомат с follow-up'ами (V2)

Задача решается в **IDE**.

**Гайд только по проведению:** внутренняя документация **«Секция general coding Java (экс livecoding / экспериментальная)»**.

- Оценка — по **общему tear-off** (не по старой балльной сетке).
- **Отличие от V1:** другие **фоллоуапы** и **критерии** результата; секция **лучше различает** уровни **15 vs 16**.
- **Старая версия:** [Femida — задача 11033](https://femida.yandex-team.ru/problems/11033)

**Время:** ориентир **~1 час**.

---

## Что сказать кандидату

- Задача: реализовать **логику банкомата**; фокус — **снятие** (`withdraw`). Пополнение и прочее — **по желанию**.
- Это **реальный** банкомат в **реальном мире** (не «виртуальный счёт»).
- Код должен быть **не стыдно показать коллегам** и **закоммитить**, без **оверинжиниринга** и длинных цепочек абстракций.
- Проверка счёта клиента **до** банкомата уже сделана; здесь — **только взаимодействие с банкоматом**.
- **Сразу подсветить:** в идеале код **расширяется** под **другие валюты** и **многопоточность**. Можно писать сразу с валютами и синхронизацией; если тяжело — **срезать** и перенести «на потом».
- **SDK — последний** фоллоуап; **не выдавать** в начале (слишком сложно).

---

## Шаблон: Java (база)

```java
/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы:
 * -  50₽, 100₽, 500₽, 1000₽, 5000₽.
 * -  20 EUR, 100 EUR, 500 EUR
 *
 * Каждая валюта обрабатывается отдельно, обмен валют банкоматом не поддерживается.
 *
 * Банкомат может использоваться многопоточно (например, резервирование выдачи денег из приложения).
 * Поддержку многопоточности можно вынести в отдельную итерацию.
 */
class ATM {
    public ??? withdraw(???) {
        // TODO implement
    }
}
```

## Шаблон: Java (фоллоуап QR)

```java
/**
 * Снятие денег по QR.
 * В банкомате нужно поддержать пару функций
 * - резервирование: вызывается с сервера. Генерирует "QR-код", который должен вернуться пользователю.
 * - снятие по QR - пользователь может прийти с этим кодом в банкомат и снять деньги.
 *
 * Резервирование производится в конкретном банкомате.
 * При параллельном резервировании и снятии баланс банкомата не должен уйти в минус.
 * Нельзя зарезервировать больше денег, чем осталось в банкомате.
 */
class ATM {
    public QR reserveQR(int amount) {}
    public ??? withdrawByQR(QR qr) {}
}
```

## Шаблон: Java (фоллоуап SDK — в конце)

```java
/**
 * Банкомат.
 * Взаимодействует с SDK, контракты для которого описаны ниже.
 * Необходимо реализовать запрос на выдачу определенной суммы (в рублях).
 * В случае, если нужную сумму выдать невозможно, отвечать отказом.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
class ATM {
    private final Sdk sdk;

    public ATM(Sdk sdk) {
        this.sdk = sdk;
        // init
    }

    // место для функции снятия
}

/**
 * Интерфейс SDK.
 * Есть нативная реализация, её писать не нужно.
 * Для тестов можно использовать StubSdk или замокировать Sdk напрямую.
 *
 * Если в сигнатурах методов чего-то не хватает - их аргументы можно изменить, не меняя смысла методов.
 */
interface Sdk {
    /**
     * Посчитать количество купюр определенного номинала в банкомате.
     * Эта операция занимает около 10 секунд, и шумная, её стоит вызывать как можно реже.
     */
    int countBanknotes(int banknote);

    void moveBanknoteToDispenser(int banknote, int count);

    void openDispenser();
}

class StubSdk implements Sdk {
    @Override
    public int countBanknotes(int banknote) {
        System.out.printf("Долго и шумно пересчитываю купюры номинала %s%n", banknote);
        return 0;
    }

    @Override
    public void moveBanknoteToDispenser(int banknote, int count) {
        System.out.printf("Перемещаю купюру %s в лоток выдачи, %s штук%n", banknote, count);
    }

    @Override
    public void openDispenser() {
        System.out.printf("Лоток выдачи открыт пользователю%n");
    }
}
```

## Шаблон: Python (база + абстрактный SDK)

```python
from abc import ABC, abstractmethod


# Банкомат: номиналы 50₽, 100₽, 500₽, 1000₽, 5000₽.
class ATM:
    pass  # место для кода


class SDK(ABC):
    @abstractmethod
    def count_banknotes(self, banknote: int) -> int:
        pass

    @abstractmethod
    def move_banknote_to_dispenser(self, banknote: int, count: int) -> None:
        pass

    @abstractmethod
    def open_dispenser(self) -> None:
        pass
```

---

## Ожидания от кандидата (база)

- Понимает, что денег в банкомате **конечное** число (без этого решение **не принимать** как готовое).
- Номиналы и хранение (можно **подсказать** «как из кошелька 8350» — со штрафом за самостоятельность).
- **Контракт** снятия: в ответе видно **сколько и чем** выдано (или явный отказ).
- **Жадный** алгоритм по убыванию номинала; более сложные — **не требовать**.
- Лаконичный код **без дублирования**.
- Минимум **один happy-path тест** с **assert**.

После достаточного объёма — **код + тесты** в интервьюшницу.

---

## Усложнение: резерв по QR

Запрос резерва с **сервера**; **многопоточность**; баланс **не в минус**; нельзя зарезервировать больше, чем есть.

Если кандидат спрашивает про многопоточность в процессе — можно **выдать** этот фоллоуап. Сразу вместе с базой — если тянет; иначе отдельно (**3–5 мин** не думает / код раздувается).

## Фоллоуап 2: SDK

**Только если осталось время.** До него почти никто не доходит; в методичке может жить как **исторический** артефакт. При ошибке SDK — **заблокировать** банкомат (по постановке).

---

## Шпаргалка: сигнатуры «полного» решения (Java)

```java
public class ATM {
    private final Map<Bill, Integer> bills;
    private final Map<String, Map<Bill, Integer>> qrHolds;

    public Map<Bill, Integer> withdraw(Currency currency, int amount) {}

    public String reserveQR(Currency currency, int amount) { return qr; }
    public Map<Bill, Integer> withdrawQR(String qr) {}
}

public class NotEnoughAtmAmountException extends RuntimeException {}
public class InvalidAmountException extends RuntimeException {}

public enum Bill {}
public enum Currency {}
```

---

## TLDR по грейдам

| Итог | Условие |
|------|---------|
| **NH** | Не решил; много помощи (tear-off «ошибки»); вмешательство в код; качество \< 3; **два нуля** в tear-off (без усложнений). |
| **14 (Hire II)** | Минимальное решение + качество **≥ 3**. |
| **15 (Hire III)** | Минимальное решение + качество **≥ 4** + **оптимальность**; **нет нулей** в tear-off (кроме усложнений). |
| **16 (Hire IV)** | Сделано **усложнение** (QR) + качество **≥ 4**; *либо* дошёл до **SDK** с любым качеством (на практике почти не встречается). |

---

## Минимальное решение (без этого не засчитывать)

По сути — **WO1 «банкомат»**:

- **Жадный** алгоритм, инициализация в **конструкторе** (или эквивалент).
- Учтена **конечность** купюр.
- Рабочий **withdraw**:
  - учитывает **остаток** купюр;
  - **минимальная** поддержка **мультивалютности** (если в постановке);
  - любая разумная **обработка ошибок**;
  - при **неуспешном** снятии **не портит** состояние;
  - работает, если **не все** заявленные номиналы заданы на входе.

**Оптимальность** (для 15):

- считает купюры через **деление**, а не лишние циклы;
- **не** пересобирает отсортированный список номиналов на **каждый** вызов;
- структуры данных без «100500 foreach»;
- субъективно — не «валенок»; если критерии **не** выполнены — **не** давать писать фоллоуапы (ошибка интервьюера).

---

## Тесты

- Смотрятся **отдельно** от кода; **грейд за тесты не качают**.
- Ожидается **хотя бы один** минимальный тест; кандидат **сам** придумывает кейсы (влияет на **качество** в tear-off).
- **Останавливать** избыточное написание тестов по времени.
- Помощь с **придумыванием** кейса — штраф как за подсказку сценария.
- Помощь с **синтаксисом** теста — **не** штрафовать как алгоритм.

---

## [Усложнение] Резерв по QR

Кандидатов мало.

**Успех:**

- метод **резервирования**, возвращающий **QR** (тип по договорённости);
- **withdraw по QR**;
- QR **аннулировать** после **успешного** снятия;
- **не** аннулировать после **неуспеха**;
- **корректная** синхронизация; баланс **не уходит в минус**.

## [Follow-up 2] SDK

- Мало кто доходит; валюты в тексте SDK можно **добавить** самому.
- Если кандидат **критикует** SDK — можно попросить **свой** контракт → доп. плюс за сильную идею.
- **Успех:** подключение SDK, **минимум** лишних `countBanknotes`, обработка **падения** любого метода SDK + **блокировка** банкомата при ошибке.

---

## Tear-off: качество (вставка в оценку)

### Придумывание решения — 0 / 0.5 / 1

Самостоятельность, API, расширяемость, тестируемость, структуры, асимптотика, corner cases.

- **0:** никакой самостоятельности; заметная помощь; неподходящие типы и не понимает после наводок.
- **0.5:** мелкие недочёты; рабочее, но неоптимально.
- **1:** без критичных проблем; оптимально или осознанно обосновано.

### Реализация — 0 / 1 / 2

Нейминг, читаемость, язык, оптимальность, валидация, API, **null**.

### Ошибки и их поиск — NH / 0 / 1 / 2

Как в общей методичке (подсказки сценариев, дебаг).

### Тестирование — 0 / 0.5 / 1

С **assert**; `main` + assert ок; **принты без проверок** — не засчитывать.

### Реализация усложнения

**1** балл при корректности без оставшихся багов.

---

## Старый tear-off (историческая справка)

Максимумы по блокам: **2** минимум, **1** валюты, **2** QR, **1** SDK, **2** мастерство, **1** оптимальность; штрафы **0** за подсказки.

Диапазоны (исторически): **[0, 2] NH**, **(2, 4] Hire II**, **(4, 6] Hire III**, **(6, ∞) Hire IV** — см. оригинальную таблицу в методичке V1.

---

## Примеры решений

Эталоны для интервьюера. **Проверяйте** ассерты и типы ключей в `Map` (Kotlin: `withdraw` возвращает `Map<Banknote, Int>`, в `main` из методички местами используются сырые `Int` — при переносе привести к одному стилю).

### Java (`ATM`, `Bill`, `ATMTest`)

```java
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATM {
    private final Map<Bill, Integer> bills;

    public ATM() {
        bills = new EnumMap<>(Bill.class);
    }

    public Map<Bill, Integer> getCurrentState() {
        return Collections.unmodifiableMap(bills);
    }

    public Map<Bill, Integer> withdraw(int amount) {
        Map<Bill, Integer> withdrawResult = new HashMap<>();
        int leftToWithdraw = amount;

        List<Bill> billsOrderedByNominal = Bill.getReverseOrderedBills();
        for (Bill bill : billsOrderedByNominal) {
            if (!bills.containsKey(bill)) {
                continue;
            }

            int maxPossibleWithdrawCount = Math.min(
                    leftToWithdraw / bill.getNominalValue(),
                    bills.get(bill));

            leftToWithdraw -= maxPossibleWithdrawCount * bill.getNominalValue();
            withdrawResult.put(bill, maxPossibleWithdrawCount);
        }

        if (leftToWithdraw == 0) {
            withdrawBills(withdrawResult);
            return withdrawResult;
        }

        throw new RuntimeException("Not enough bills");
    }

    public void deposit(Map<Bill, Integer> stackOfBills) {
        stackOfBills.forEach(this::depositBill);
    }

    private void depositBill(Bill bill, int count) {
        this.bills.merge(bill, count, Integer::sum);
    }

    private void withdrawBills(Map<Bill, Integer> stackOfBills) {
        stackOfBills.forEach(
                (bill, count) -> this.bills.merge(bill, -count, Integer::sum));
    }
}
```

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Bill {
    B_50(50),
    B_100(100),
    B_500(500),
    B_1000(1000),
    B_5000(5000),
    ;

    private final int nominalValue;

    Bill(int nominalValue) {
        this.nominalValue = nominalValue;
    }

    public int getNominalValue() {
        return nominalValue;
    }

    public static List<Bill> getReverseOrderedBills() {
        return Arrays.stream(Bill.class.getEnumConstants())
                .sorted(Comparator.comparing(Bill::getNominalValue, Comparator.reverseOrder()))
                .toList();
    }
}
```

```java
import java.util.Map;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ATMTest {
    private final Map<Bill, Integer> depositBills = Map.of(
            Bill.B_100, 5,
            Bill.B_500, 10,
            Bill.B_1000, 17);

    @Test
    public void testDeposit() {
        ATM atm = new ATM();
        atm.deposit(depositBills);
        assertThat(atm.getCurrentState())
                .usingRecursiveComparison()
                .isEqualTo(depositBills);
    }

    @Test
    public void testWithdraw() {
        ATM atm = new ATM();
        atm.deposit(depositBills);

        Map<Bill, Integer> withdrawResult = atm.withdraw(3700);
        assertThat(withdrawResult)
                .usingRecursiveComparison()
                .isEqualTo(
                        Map.of(
                                Bill.B_100, 2,
                                Bill.B_500, 1,
                                Bill.B_1000, 3));

        assertThat(atm.getCurrentState())
                .usingRecursiveComparison()
                .isEqualTo(
                        Map.of(
                                Bill.B_100, 3,
                                Bill.B_500, 9,
                                Bill.B_1000, 14));
    }

    @Test
    public void testWithdrawNotEnoughBillsInAtm() {
        ATM atm = new ATM();
        atm.deposit(depositBills);
        assertThatThrownBy(() -> atm.withdraw(1_000_000))
                .isInstanceOf(RuntimeException.class);
    }
}
```

### Python

```python
# atm.py
from enum import IntEnum

from sdk import SDK


class Banknote(IntEnum):
    B50 = 50
    B100 = 100
    B500 = 500
    B1000 = 1000
    B5000 = 5000


class ATM:
    def __init__(self, sdk: SDK) -> None:
        self._sdk = sdk
        self._state = {}
        for banknote in Banknote:
            self._state[banknote] = self._sdk.count_banknotes(banknote.value)

    def withdraw(self, amount: int) -> dict[Banknote, int]:
        if amount <= 0:
            raise RuntimeError("Amount must be a positive number.")

        result = {}
        rest = amount
        for banknote, available in sorted(self._state.items(), reverse=True):
            if not available:
                continue

            required = rest // banknote.value
            if not required:
                continue

            to_substract = min(available, required)
            rest -= to_substract * banknote.value
            result[banknote] = to_substract

        if rest != 0:
            raise RuntimeError("Cannot withdraw the requested amount.")

        for banknote, count in result.items():
            self._sdk.move_banknote_to_dispenser(banknote.value, count)
            self._state[banknote] -= count

        self._sdk.open_dispenser()

        return result
```

```python
# sdk.py
from abc import ABC, abstractmethod


class SDK(ABC):
    @abstractmethod
    def count_banknotes(self, banknote: int) -> int:
        pass

    @abstractmethod
    def move_banknote_to_dispenser(self, banknote: int, count: int) -> None:
        pass

    @abstractmethod
    def open_dispenser(self) -> None:
        pass
```

```python
# test_atm.py
import pytest

from atm import ATM
from sdk import SDK


class SDKStub(SDK):
    def __init__(self, state: dict[int, int]) -> None:
        self.state = state

    def count_banknotes(self, banknote: int) -> int:
        return self.state.get(banknote, 0)

    def move_banknote_to_dispenser(self, banknote: int, count: int) -> None:
        self.state[banknote] -= count

    def open_dispenser(self) -> None:
        pass


def test_withdraw_succeed():
    sdk = SDKStub({
        100: 10,
        500: 10,
        1000: 10,
    })

    atm = ATM(sdk)
    atm.withdraw(2800)

    assert sdk.state == {
        100: 7,
        500: 9,
        1000: 8,
    }


def test_withdraw_fail():
    atm = ATM(SDKStub({
        100: 1,
        500: 2,
        1000: 3,
    }))

    with pytest.raises(RuntimeError):
        atm.withdraw(3300)
```

### Kotlin + SDK

Полный листинг из методички (`Atm`, `Banknote`, `Sdk`, `StubSdk`, `main`) — при копировании сверьте **`withdraw(600)`** (ожидаемое разложение по купюрам) и текст **`NotEnoughMoneyException`** с фактическим `StubSdk` и семантикой поля `rest` в `validate`.

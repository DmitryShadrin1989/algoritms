# Фильтрация уведомлений (General coding Java, livecoding)

Задача на **написание кода в IDE** кандидата. Расчёт: **~60 минут**.

Состоит из **базовой части** и **усложнения** (асинхронность / таймаут истории).

- **15 грейд:** ожидается успеть **базовую** часть.
- **16 грейд:** ожидается **усложнение**.

---

## Постановка (основная часть)

```text
Компания предоставляет сервис массовой рассылки уведомлений для других бизнесов.
К вам обратился product owner с задачей создать систему фильтрации уведомлений с учетом предпочтений пользователей.

## Определения

Уведомление:
- id уведомления
- тип уведомления (EMAIL, SMS, PUSH)
- получатель (id пользователя)
- текст сообщения

Получатель может иметь настройки предпочтений:
- разрешенные каналы уведомлений (список типов)
- заблокированные отправители (список id отправителей)

История отправленных уведомлений:
- список уведомлений, отправленных пользователю

## Важно
Настройки пользователей и история уведомлений предоставляются другими компонентами системы.
Вам необходимо спроектировать контракты для получения этих данных.
Реализацию хранения делать не нужно.

## Задача
Написать систему фильтрации уведомлений, которая:
- на вход получает список уведомлений для фильтрации и id отправителя
- исключает уведомления, не соответствующие предпочтениям получателя
- реализует защиту от дубликатов сообщений, в том числе учитывая сообщения, отправленные за последние 24 часа. Дубликатами считаются сообщения с одинаковыми id, адресованные одному и тому же пользователю
- возвращает отфильтрованный список уведомлений, готовых к отправке.

Отправка уведомлений не входит в вашу задачу - другая команда займется отправкой отфильтрованного списка.
Ваша задача - только фильтрация.
```

### Скелет для кандидата

```java
class NotificationFilter {
    UserSettingsService userSettingsService;
    NotificationHistoryService notificationHistoryService;

    public ??? filter(??? senderId, List<???> msgs) {
        // TODO implement
    }
}

interface UserSettingsService {
    // TODO any functions
}

interface NotificationHistoryService {
    // TODO any functions
}
```

*(Имена сервисов в скелете — `UserSettings*`; в эталонном решении ниже используются `UserPreferences*` — кандидат может выбрать свои названия, главное — ясный контракт.)*

---

## Частые вопросы кандидата (FAQ)

| Вопрос | Ответ |
|--------|--------|
| Реализовывать сервисы настроек и истории? | **Нет**, достаточно **интерфейсов**. |
| Логика часовых поясов? | **Нет.** Можно оперировать **timestamp** или возложить выборку «за сутки» на сервис истории. |
| Нет настроек у пользователя? | По умолчанию **разрешены все каналы**. |
| Приоритет фильтров? | Кандидат определяет сам; можно **обсудить** продуктовую логику. |
| Критерий дубликата? | Одинаковый **id уведомления** для **одного и того же пользователя**. |
| Глубина дедупликации? | **Последние 24 часа** истории + текущий запрос. |
| Дубликаты **внутри одного запроса**? | Оставить **одно** (любое). |
| После кода | Попросить **скопировать в интервьюшницу** код + **тесты**. |

---

## Усложнение

```text
Настройки пользователей и история отправленных уведомлений хранятся во внешних сервисах.
Доступ к сервисам асинхронный и может быть медленным.

Нужно адаптировать код к этим условиям.
Если сервис истории отвечает больше 3 секунд - необходимо применить частичную фильтрацию, только проверку по настройкам пользователя.
```

### Подсказки интервьюеру (не навязывать кандидату заранее)

- Сервис **настроек** недоступен → **настройки по умолчанию**.
- Сервис **истории** недоступен → **пропустить дедупликацию** (или эквивалент по смыслу).
- **Кеш** — хорошая практика, не обязательна.
- **Батчинг** запросов по пользователям — плюс.
- Должен ли главный метод стать **асинхронным** — решает кандидат.

**Обратить внимание:** `CompletableFuture` (или аналог), исключения в асинхронных цепочках, **тесты** асинхронного кода.

**Сигнал на повышенный грейд:** эффективная **группировка запросов** по пользователям.

### Когда выдавать усложнение

Можно сразу с базой, если кандидат тянет и думает про асинхронность. Если не тянет — отдельным блоком (**3–5 минут** не может придумать или код непомерно громоздкий).

---

## TLDR по оценке (грейды)

| Итог | Условие |
|------|---------|
| **NH** | Не решил; слишком много помощи (см. tear-off «ошибки и их поиск»); пришлось вмешиваться в код; решение \< 3 по качеству; **два нуля** в tear-off (без учёта усложнения). |
| **14** | Минимальное решение + качество решения **≥ 3**. |
| **15** | Минимальное решение + качество **≥ 4**, **нет нулей** в tear-off (кроме усложнения). |
| **16** | Сделано **усложнение** + качество **≥ 4**. |

Подробная методика — во внутренней документации секции, блок **#tear-off**.

---

## Минимальное решение (без чего не принять)

1. Фильтрация по **заблокированному отправителю**.
2. Фильтрация по **типу канала** (разрешённые каналы).
3. **Дедупликация** с учётом **текущего списка** на отправку **и** истории за **24 часа**.

### Требования для 15 грейда

Отдельных порогов нет — смотреть **tear-off**.

### Реализация усложнения (что проверять)

- Корректный вызов через **CompletableFuture** / аналог.
- Обработка ошибок в **асинхронном** стиле.
- Обработка **таймаута** истории (**\> 3 с** → только фильтр по настройкам).
- Запросы **настроек** и **истории** — **параллельно** (где уместно).

---

## Tear-off: качество (кратко)

### Придумывание решения (0 / 0.5 / 1)

Самостоятельность, API, расширяемость, тестируемость, структуры данных, асимптотика. Учёт corner cases и runtime-исключений.

### Реализация решения (0 / 1 / 2)

Нейминг, читабельность, язык, оптимальность, валидация, ошибки API, **null**-безопасность.

### Ошибки и их поиск (NH / 0 / 1 / 2)

NH при помощи с кодом или **≥ 5** подсказанных сценариев; **0** — **≥ 3** подсказок; **1** — одна подсказка; **2** — мало багов до сдачи, точечный дебаг.

### Тестирование (0 / 0.5 / 1)

Обязательны **assert**-проверки; печать без проверок — не засчитывать; можно `main` + assert/исключения при отсутствии JUnit.

### Реализация усложнения

Один целый балл при корректности без оставшихся багов.

---

## Примеры тестов: основная часть

Кандидат **физически не успеет** все; ориентир для интервьюера.

**Валидация:** `null` / пустой список уведомлений, `senderId`, поля уведомлений; ошибки сервисов.

**Happy path:** блокировка отправителя; фильтр каналов; дедуп по id.

**Дополнительно:** нет настроек → дефолт; комбинация фильтров; пустой вход → пустой выход; смешанный проход/не проход; дедуп с учётом **дубликатов в запросе**.

**Проектирование:** не запрашивать **историю**, если уже отсекли по простым правилам (например заблокированный отправитель) — иначе снижать оценку проектирования.

---

## Примеры тестов: усложнение

**Ошибки:** сеть / сбой сервиса настроек; сеть / сбой истории.

**Happy path:** успешная загрузка настроек; фильтрация + дедуп с историей.

**Дополнительно:** асинхронный успех; ошибка настроек; ошибка истории; недоступность настроек → дефолт; недоступность / таймаут истории → **частичная** фильтрация.

---

## Эталон: основная часть (код близкий к максимальному качеству)

```java
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

// Данные
public enum NotificationType {
    EMAIL, SMS, PUSH
}

public record Notification(
        @NonNull String id,
        @NonNull NotificationType type,
        @NonNull String userId,
        @NonNull String message
) {}

public record UserPreferences(
        @NonNull Set<NotificationType> allowedChannels,
        @NonNull Set<String> blockedSenders
) {
    public static UserPreferences getDefault() {
        return new UserPreferences(
                EnumSet.allOf(NotificationType.class),
                Collections.emptySet()
        );
    }
}

// Сервисы
public interface UserPreferencesService {
    Optional<UserPreferences> getUserPreferences(String userId);
}

public interface NotificationHistoryService {
    List<Notification> getNotificationsForLast24Hours(String userId);
}

@RequiredArgsConstructor
public class NotificationFilterService {
    private final UserPreferencesService preferencesService;
    private final NotificationHistoryService historyService;

    public List<Notification> filterNotifications(
            @NonNull List<Notification> notifications,
            @NonNull String senderId) {

        Map<String, List<Notification>> notificationsByUser = notifications.stream()
                .collect(Collectors.groupingBy(Notification::userId));

        List<Notification> result = new ArrayList<>();

        for (Map.Entry<String, List<Notification>> entry : notificationsByUser.entrySet()) {
            String userId = entry.getKey();
            List<Notification> userNotifications = entry.getValue();

            result.addAll(filterNotificationsForUser(userNotifications, userId, senderId));
        }

        return result;
    }

    private List<Notification> filterNotificationsForUser(
            List<Notification> notifications,
            String userId,
            String senderId) {

        final UserPreferences preferences;
        preferences = preferencesService.getUserPreferences(userId).orElse(UserPreferences.getDefault());

        if (preferences.blockedSenders().contains(senderId)) {
            return Collections.emptyList();
        }

        List<Notification> channelFiltered = notifications.stream()
                .filter(n -> preferences.allowedChannels().contains(n.type()))
                .toList();

        List<Notification> history;
        try {
            history = historyService.getNotificationsForLast24Hours(userId);
            if (history == null) {
                history = Collections.emptyList();
            }
        } catch (Exception e) {
            history = Collections.emptyList();
        }

        Set<String> sentNotificationIds = history.stream()
                .map(Notification::id)
                .collect(Collectors.toSet());

        Set<String> currentRequestIds = new HashSet<>();
        List<Notification> deduplicated = new ArrayList<>();

        for (Notification notification : channelFiltered) {
            if (!sentNotificationIds.contains(notification.id()) &&
                    !currentRequestIds.contains(notification.id())) {
                deduplicated.add(notification);
                currentRequestIds.add(notification.id());
            }
        }

        return deduplicated;
    }
}
```

### Эталон: тесты (JUnit + Mockito)

```java
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationFilterServiceTest {

    @Mock
    private UserPreferencesService preferencesService;

    @Mock
    private NotificationHistoryService historyService;

    @InjectMocks
    private NotificationFilterService filterService;

    @Test
    void shouldFilterByBlockedSender() {
        String senderId = "blocked-sender";
        String userId = "user1";

        UserPreferences preferences = new UserPreferences(
                EnumSet.allOf(NotificationType.class),
                Set.of(senderId)
        );

        when(preferencesService.getUserPreferences(userId)).thenReturn(Optional.of(preferences));

        List<Notification> notifications = List.of(
                new Notification("1", NotificationType.EMAIL, userId, "Test")
        );

        List<Notification> result = filterService.filterNotifications(notifications, senderId);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFilterByAllowedChannels() {
        String senderId = "sender1";
        String userId = "user1";

        UserPreferences preferences = new UserPreferences(
                Set.of(NotificationType.EMAIL),
                Collections.emptySet()
        );

        when(preferencesService.getUserPreferences(userId)).thenReturn(Optional.of(preferences));
        when(historyService.getNotificationsForLast24Hours(userId))
                .thenReturn(Collections.emptyList());

        List<Notification> notifications = List.of(
                new Notification("1", NotificationType.EMAIL, userId, "Test"),
                new Notification("2", NotificationType.SMS, userId, "Test")
        );

        List<Notification> result = filterService.filterNotifications(notifications, senderId);

        assertEquals(1, result.size());
        assertEquals(NotificationType.EMAIL, result.get(0).type());
    }

    @Test
    void shouldDeduplicateNotifications() {
        String senderId = "sender1";
        String userId = "user1";

        UserPreferences preferences = UserPreferences.getDefault();

        List<Notification> history = List.of(
                new Notification("duplicate", NotificationType.EMAIL, userId, "Old")
        );

        when(preferencesService.getUserPreferences(userId)).thenReturn(Optional.of(preferences));
        when(historyService.getNotificationsForLast24Hours(userId)).thenReturn(history);

        List<Notification> notifications = List.of(
                new Notification("duplicate", NotificationType.EMAIL, userId, "Test"),
                new Notification("new", NotificationType.EMAIL, userId, "Test")
        );

        List<Notification> result = filterService.filterNotifications(notifications, senderId);

        assertEquals(1, result.size());
        assertEquals("new", result.get(0).id());
    }

    // TODO: несколько пользователей с разными настройками
    // TODO: пустой вход = пустой выход
    // TODO: null в полях — исключения
    // TODO: настройки не заданы — дефолт
    // TODO: исключение от сервиса настроек пробрасывается
    // TODO: исключение от истории — работа без истории
    // TODO: пустые исключения в настройках — все каналы разрешены (уточнить формулировку)
}
```

---

## Заметки по эталону

- **Порядок фильтров:** сначала блокировка отправителя — **история не запрашивается**, если пользователь всё равно не получит рассылку.
- В постановке для кандидата про исключения настроек сказано «пробрасываем дальше»; в FAQ усложнения — при недоступности настроек **дефолт**. На интервью **согласовать** единое правило с кандидатом.
- Имена `UserSettingsService` в скелете и `UserPreferencesService` в эталоне — намеренно показать, что важен **контракт**, а не имя класса.

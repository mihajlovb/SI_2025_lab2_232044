# SI_2025_lab2_232044
---
Borjan Mihajlov 232044
---
## Control Flow Graph
![image](https://github.com/user-attachments/assets/6ce537ec-8713-4b26-a510-f33fac217c01)


## Цикломатска комплексност
Цикломатската комплексност на овој код е 9, истата ја добив преку формулата P+1, каде што P е бројот на предикатни јазли (обоени со темно сина боја). Во случајoв P=8, па цикломатската комплексност изнесува 9.

## Тест случаи според критериумот Every statement
---

### Тест 1: `allItems == null`

```java
allItems = null  
cardNumber = "9876543210123456"
```

Овој тест директно го активира условот на линија 52:

```java
if (allItems == null)
```

Фрла:

```
allItems list can't be null!
```
---

### Тест 2: `item.getName()` е празен стринг

```java
allItems = [Item("", 3, 80, 0)]  
cardNumber = "9876543210123456"
```

Овој тест влегува во for-циклусот, и фрла exception на линија 58:

```java
if (item.getName() == null || item.getName().length() == 0)
```

Фрла:

```
Invalid item!
```
---

### Тест 3: невалидна должина на cardNumber (15 цифри)

```java
allItems = [Item("Laptop", 2, 450, 0.15)]  
cardNumber = "98765432101234"
```

Овој тест не го задоволува условот:

```java
if (cardNumber != null && cardNumber.length() == 16)
```

и оди во `else`, каде фрла:

```
Invalid card number!
```
---

### Тест 4: невалиден карактер во cardNumber

```java
allItems = [Item("IdeaPad", 1, 90, 0)]  
cardNumber = "12a4b6cd89efghij"
```

Овој тест содржи недозволени карактери. Го активира:

```java
if (allowed.indexOf(c) == -1)
```

Фрла:

```
Invalid character in card number!
```
---

### Тест 5: валиден случај (никој услов не фрла exception)

```java
allItems = [Item("Marker", 4, 30, 0)]  
cardNumber = "9876543210123456"
```

Овој тест влегува во сите блокови без да активира ниту еден exception. Се пресметува финалната сума и се враќа преку:

```java
return sum;
```
---

Со овие 5 тест случаи се покриваат сите statements од кодот.


## Тест случаи според критериумот Multiple Condition

Условот кој се тестира е:

```java
if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
```

Овој услов има 3 предикати, што значи постојат 2^3 = 8 можни комбинации. Но, според критериумот **Multiple Condition**, доволно е да се покријат **сите различни извршни патишта**, што може да се постигне со **минимум 4 тест случаи**.

---

### Тест MC1: T X X (само првиот услов е вистинит)

```java
allItems = [Item("Camera", 1, 400, 0)]  
cardNumber = "1234432112344321"
```

* item.getPrice() > 300 → true
* item.getDiscount() = 0 → false
* item.getQuantity() = 1 → false

Активира се телото на if-условот.

---

### Тест MC2: F T X (само вториот услов е вистинит)

```java
allItems = [Item("Speaker", 1, 100, 0.2)]  
cardNumber = "3210321032103210"
```

* item.getPrice() = 100 → false
* item.getDiscount() = 0.2 → true
* item.getQuantity() = 1 → false

Активира се телото на if-условот.

---

### Тест MC3: F F T (само третиот услов е вистинит)

```java
allItems = [Item("Backpack", 11, 100, 0)]  
cardNumber = "0000111122223333"
```

* item.getPrice() = 100 → false
* item.getDiscount() = 0 → false
* item.getQuantity() = 11 → true

Активира се телото на if-условот.

---

### Тест MC4: F F F (сите услови се лажни)

```java
allItems = [Item("PenCase", 2, 100, 0)]  
cardNumber = "9999888877776666"
```

* item.getPrice() = 100 → false
* item.getDiscount() = 0 → false
* item.getQuantity() = 2 → false

**Не се активира** телото на if-условот.

---

Со овие 4 тест случаи се покриваат сите можни патишта на Multiple Condition условот.


## Објаснување на напишаните unit tests

Во класа `SILab2Test` се дефинирани две методи со JUnit анотации:

### `testEveryStatement()`

Оваа функција ги содржи 5 тест случаи кои:

* Покриваат ситуации каде се фрлаат исклучоци (`null` листа, празно име, невалидна картичка)
* Прават пресметка на сума со и без попуст
* Влегуваат во сите услови и блокови од кодот

На овој начин, преку различни сценарија, се осигуруваме дека **секоја изјава од кодот е извршена барем еднаш**.

### `testMultipleCondition()`

Оваа функција содржи 4 тест случаи, секој со различна комбинација на условите од изразот:

```java
item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10
```

Со тоа, се покриваат сите можни исходи на Multiple Condition условот, без да се пишуваат сите 8 комбинации. Се валидара логиката за кога условот треба да биде активен и кога не, според специфични влезни вредности.

Двете методи користат `assertEquals` и `assertThrows` за проверка на резултати или очекувани исклучоци, според JUnit 5 синтакса.


##Лабораторная 2-3##
***

Используемое API - https://docs.openaq.org/#api-Cities-GetV1Cities

**Пример** 

JSON обьекта



    "city": "Amsterdam",
    "country": "NL",
    "count": 21301,
    "locations": 14
  .

    "city": "Badhoevedorp",
    "country": "NL",
    "count": 2326,
    "locations": 1
 



***
**Пример запроса**

https://api.openaq.org/v1/cities/?country=NL&limit=12

В качестве названия стран использовать 2-ух буквенный код.

Например:

* NL - Нидерланды
* GB - Великобритания
* FR - Франция


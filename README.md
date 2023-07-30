# cinema room rest service
#### A streamlined REST application designed to facilitate the management of a cinema room. This application enables users to seamlessly purchase or refund seats, while affording cinema managers the privilege of accessing cinema statistics, including income generation and the count of available and purchased seats. These operations are securely accessible through designated request endpoints and are protected by a straightforward authorization process.
---
##### Example of endpoints responses (all requests assume that the cinema room is of size 9x9 (rowXcolumn)):
---
###### `/seats`:
```
{
    "total_rows": 9,
    "total_columns": 9,
    "available_seats": [
        {
            "row": 1,
            "column": 1,
            "price": 10
        },
        {
            "row": 1,
            "column": 2,
            "price": 10
        },
        ...
        {
            "row": 9,
            "column": 9,
            "price": 8
        }
    ]
}
```
---
###### `/purchase` with status 200:
```
{
    "ticket": {
        "row": 1,
        "column": 4,
        "price": 10
    },
    "token": "d4c14ba8-0ccd-4d2c-80a3-17a6016c9fb3"
}
```
---
###### `/purchase` with status 400 #1 (out of range row/column):
```
{
    "error": "The number of a row or a column is out of bounds!"
}
```
---
###### `/purchase` with status 400 #2 (already bought seat/ticket):
```
{
    "error": "The ticket has been already purchased!"
}
```
---
###### `/return` with status 200:
```
{
    "returned_ticket": {
        "row": 1,
        "column": 5,
        "price": 10
    }
}
```
---
###### `/return` with status 400 (wrong ticket token - unique identifier):
```
{
    "error": "Wrong token!"
}
```
---
###### `/stats` with status 200:
```
{
    "current_income": 10,
    "number_of_available_seats": 80,
    "number_of_purchased_tickets": 1
}
```
---
###### `/stats` with status 401 (unauthorized - wrong password):
```
{
    "error": "The password is wrong!"
}
```

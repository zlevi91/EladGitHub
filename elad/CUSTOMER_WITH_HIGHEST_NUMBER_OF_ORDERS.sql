
SELECT CUSTOMERS.COMPANY_NAME, ORDER_COUNT FROM
(SELECT CUSTOMER_ID, COUNT(*) AS ORDER_COUNT FROM ORDERS
GROUP BY CUSTOMER_ID
ORDER BY ORDER_COUNT DESC) STEP1 INNER JOIN CUSTOMERS
ON STEP1.CUSTOMER_ID = CUSTOMERS.CUSTOMER_ID
WHERE ROWNUM = 1;
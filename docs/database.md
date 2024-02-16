# Database - [(Voltar ao README)](../README.md)

Estas são as que serão mantidas para atender as funcionalidades do sistema de venda de ingressos.
Este diagrama foi criado a partir do site [dbdiagram.io](https://dbdiagram.io/).

<details>
  <summary>Code diagram</summary>

  ```markdown

// Use DBML to define your database structure
// Docs: https://dbml.dbdiagram.io/docs

Table clients {
  id bigint [primary key, increment]
  name varchar(255) [not null]
  email varchar(255) [not null]
  document_number varchar(14) [not null]
  document_type int [not null, note: '1 = PF, 2 = PJ']
  created_at timestamp [not null, default: 'CURRENT_TIMESTAMP']
}

Table orders {
  id bigint [primary key, increment]
  client_id bigint [ref: > clients.id, not null]
  ticket_id bigint [ref: > tickets.id, not null]
  qty integer [not null]
  total decimal(10,2) [not null]
  status integer [not null, note: '1=created, 2=paid, 3=canceled, 4=expired']
  pix_emv varchar(255)
  created_at timestamp [not null, default: 'CURRENT_TIMESTAMP']
}

Table tickets {
  id bigint [primary key, increment]
  title varchar(255) [not null]
  status integer [not null, note: '1=open to sale, 2=closed to sale']
  price decimal(10,2) [not null]
  qty_available integer [not null]
  created_at timestamp [not null, default: 'CURRENT_TIMESTAMP']
}

  ```
  
</details>

---

![Database](./images/database.png)

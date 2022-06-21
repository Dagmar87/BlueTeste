# BlueTeste
Teste do processo seletivo da Blue Technology 

Nome: José Dagmar FSS

COMANDOS:

- Para rodar o projeto Spring Boot, usar esse comando:

  cd agenda
  mvn spring-boot:run

- Mas antes disso, para rodar primeiro o banco de dados PostgreSQL do projeto, usar esse comando:

  psql -h localhost -U postgres -d agendadb

- Se o banco não foi criado, usar esse comando:

  sudo -u postgres createdb agendadb

- Para rodar a parte de Front-End (Vue) do Projeto, usar esse comando:

  cd agenda-vue
  npm run serve

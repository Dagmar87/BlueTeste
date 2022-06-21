<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" required v-model="agenda.nome" name="nome" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" required v-model="agenda.email" name="email" />
            </div>
            <div class="form-group">
                <label for="telefone">Telefone</label>
                <input type="text" class="form-control" id="telefone" required v-model="agenda.telefone"
                    name="telefone" />
            </div>
            <button @click="saveAgenda" class="btn btn-success">Enviar</button>
        </div>
        <div v-else>
            <h4>Você enviou com sucesso!</h4>
            <button class="btn btn-success" @click="newAgenda">Adicionar</button>
        </div>
    </div>
</template>

<script>
import AgendaDataService from "../services/AgendaDataService";

export default {
    name: "add-agenda",
    data() {
        return {
            agenda: {
                id: null,
                nome: "",
                email: "",
                telefone: ""
            },
            submitted: false
        };
    },
    methods: {
        saveAgenda() {
            var data = {
                nome: this.agenda.nome,
                email: this.agenda.email,
                telefone: this.agenda.telefone
            };
            AgendaDataService.create(data)
                .then(response => {
                    this.agenda.id = response.data.id;
                    console.log(response.data);
                    this.submitted = true;
                })
                .catch(e => {
                    console.log(e);
                });
        },
        newAgenda() {
            this.submitted = false;
            this.agenda = {};
        }
    }

};
</script>
<style>
.submit-form {
    max-width: 300px;
    margin: auto;
}
</style>
<template>
    <div v-if="currentAgenda" class="edit-form">
        <h4>Contato</h4>
        <form>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" v-model="currentAgenda.nome" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" v-model="currentAgenda.email" />
            </div>
            <div class="form-group">
                <label for="telefone">Telefone</label>
                <input type="text" class="form-control" id="telefone" v-model="currentAgenda.telefone" />
            </div>
        </form>
        <button class="badge badge-danger mr-2" @click="deleteAgenda">
            Excluir
        </button>
        <button type="submit" class="badge badge-success" @click="updateAgenda">
            Atualizar
        </button>
        <p>{{ message }}</p>
    </div>
    <div v-else>
        <br />
        <p>Por favor, clique em um contato...</p>
    </div>
</template>
<script>
import AgendaDataService from "../services/AgendaDataService";

export default {
    name: "agenda",
    data() {
        return {
            currentAgenda: null,
            message: ''
        };
    },
    methods: {
        getAgenda(id) {
            AgendaDataService.get(id)
                .then(response => {
                    this.currentAgenda = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        updateAgenda() {
            AgendaDataService.update(this.currentAgenda.id, this.currentAgenda)
                .then(response => {
                    console.log(response.data);
                    this.message = 'O contato foi atualizado com sucesso!';
                })
                .catch(e => {
                    console.log(e);
                });
        },
        deleteAgenda() {
            AgendaDataService.delete(this.currentAgenda.id)
                .then(response => {
                    console.log(response.data);
                    this.$router.push({ name: "agendas" });
                })
                .catch(e => {
                    console.log(e);
                });
        }
    },
    mounted() {
        this.message = '';
        this.getAgenda(this.$route.params.id);
    }
};
</script>
<style>
.edit-form {
    max-width: 300px;
    margin: auto;
}
</style>
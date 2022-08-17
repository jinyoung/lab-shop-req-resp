<template>

    <v-data-table
            :headers="headers"
            :items="orderDetail"
            :items-per-page="5"
            class="elevation-1"
    ></v-data-table>

</template>

<script>

    export default {
        name: 'OrderDetailList',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { 
                    text: "orderItemId", 
                    value: "orderItemId" 
                },
                { 
                    text: "productId", 
                    value: "productId" 
                },
                { 
                    text: "qty", 
                    value: "qty" 
                },
            ],
            orderDetail : [],
        }),
        async created() {
            if(!this.value) this.value = [];
        },
        methods: {
        }
    }
</script>


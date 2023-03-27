$("form[role='form']").validate({
    rules:{
        "id": {
            required: true,
        },
        "cost": {
            required: true,
        },
        "certName": {
            required: true,
        },

    },
    messages: {
        "id": {
            required: "id is not empty!",
        },
        "cost": {
            required: "cost is not empty!",
        },
        "certName": {
            required: "cert name is not empty!",
        },

    }

})
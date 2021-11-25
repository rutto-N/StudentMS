let loginForm = {

    form: {
        formTitle: 'Login',
        fields:
            [

                {
                    label:"Email",
                    name:"username",
                    id:"username",
                    type:"text",
                    required:true,
                },
                {
                    label:"Password",
                    name:"password",
                    id:"password",
                    type:"password",
                    required:true,
                }


            ],
        buttons:
            [{
                type: 'button',
                method: "POST",
                url: './login',
                value: 'Login',
                showMsg: 'showErrorMsg',
                id: 'user.submit',

            }]


    },



}
AppComponents.htmlForm.render.call(loginForm.form);
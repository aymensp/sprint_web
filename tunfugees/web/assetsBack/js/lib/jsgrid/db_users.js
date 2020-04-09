(function () {

    var db_users = {

        loadData: function (filter) {
            return $.grep(this.users, function (client) {
                return (!filter.Username || client.Username.indexOf(filter.Username) > -1)
                    && (filter.Email || client.Email === filter.Email)
                    && (!filter.Nationality || client.Nationality.indexOf(filter.Nationality) > -1)
                    && (!filter.Firstname || client.Firstname === filter.Firstname)
                    && (filter.Lastname|| client.Lastname === filter.Lastname);
            });
        },

        insertItem: function (insertingClient) {
            this.users.push(insertingClient);
        },

        updateItem: function (updatingClient) {
        },

        deleteItem: function (deletingClient) {
            var clientIndex = $.inArray(deletingClient, this.users);
            this.users.splice(clientIndex, 1);
        }

    };

    db_users.users = [];
    for (var i = 0; i < data_users.length; i++) {
        db_users.users.push({
            "ID": data_users[i].id,
            "Username": data_users[i].username,
            "Nationality": data_users[i].nationality,
            "E-mail": data_users[i].email,
            "First Name" : data_users[i].firstname,
            "Last Name" : data_users[i].lastname
        });
    }
    window.db_users = db_users;
    console.log(data_users[1].email);

}());
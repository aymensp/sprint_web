(function () {

    var db_games = {

        loadData: function (filter) {
            return $.grep(this.games, function (client) {
                return (!filter.id || client.id.indexOf(filter.id) > -1)

            });
        },

        insertItem: function (insertingClient) {
            var formData = new FormData();
            for ( var key in insertingClient ) {
                formData.append(key, insertingClient[key]);
            }
            formData.append("summary_photo",document.getElementById('summary_photo_insert_new').files[0]);
            $.ajax({
                type: "post",
                url: Routing.generate('game_new'),
                data:   formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    if(data.success === true){
                        insertingClient['Summary_Photo'] = data.url;
                        this.games.push(insertingClient);
                    }
                }
            });
        },

        updateItem: function (updatingClient) {
            console.log(updatingClient);
            var formData = new FormData();
            for ( var key in updatingClient ) {
                formData.append(key, updatingClient[key]);
            }
            if(document.getElementById('summary_photo_edit_'+updatingClient.Id).files.length > 0){
                formData.append("summary_photo_new",document.getElementById('summary_photo_edit_'+updatingClient.Id).files[0]);
            }
            $.ajax({
                type: "post",
                url: Routing.generate('games_edit', {id: updatingClient.Id}),
                data:   formData,
                contentType: false,
                processData: false
            });
        },

        deleteItem: function (deletingClient) {
            $.ajax({
                type: "get",
                url: Routing.generate('game_delete', {id: deletingClient.Id}),
                data:   "deletedGame="+JSON.stringify(deletingClient),
                contentType: "application/j-son;charset=UTF-8",
                success:    function (data) {
                    if(data.success === true){
                        var clientIndex = $.inArray(deletingClient, db_games.games);
                        this.games.splice(clientIndex, 1);
                    }
                }
            });
        }

    };

    db_games.games = [];
    for (var i = 0; i < data_games.length; i++) {
        db_games.games.push({
            "Check": data_games[i].id,
            "Id": data_games[i].id,
            "Home Team": data_games[i].hometeam.id,
            "Away Team": data_games[i].awayteam.id,
            "Result": data_games[i].result,
            "Date": new Date(data_games[i].date).toLocaleDateString("fr-FR"),
            "Summary": data_games[i].summary,
            "Summary Photo": data_games[i].summaryphoto,
            "Highlights": data_games[i].highlights,
            "Referee": data_games[i].referee,
            "Squads": data_games[i].squads,
            "Stadium": data_games[i].stadium.id

        });
    }
    db_games.teams = [];
    for (var i = 0; i < data_teams.length; i++) {
        db_games.teams.push({
            "ID": data_teams[i].id,
            "Name": data_teams[i].name
        });
    }
    db_games.stadiums = [];
    for (var i = 0; i < data_stadiums.length; i++) {
        db_games.stadiums.push({
            "ID": data_stadiums[i].id,
            "Name": data_stadiums[i].name
        });
    }
    window.db_games= db_games;

}());
$(function () {

    $("#jsGrid").jsGrid({
        height: "100%",
        width: "100%",
        filtering: false,
        editing: true,
        inserting: true,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 10,
        pageButtonCount: 5,
        deleteConfirm: "Do you really want to delete this game?",
        controller: db_games,
        fields: [
            {
                name: "Check",editing:false,
            width: 60, type: "radio", itemTemplate: function (item,itemIndex) {
                    return $("<input>").attr('type', 'radio').attr('name', 'squad_select[]').attr('value', item).attr('data-gameid',itemIndex.Id).attr('onclick','getGameData('+itemIndex.Id+')');
                }
            },
            {name: "Id", visible: false, type: "text"},
            {name: "Home Team", type: "select",
                items: db_games.teams,
                valueField: "ID",
                textField: "Name",
                width: 110
            },
            {name: "Away Team", type: "select",
                items: db_games.teams,
                valueField: "ID",
                textField: "Name",
                width: 110
            },
            {name: "Result", type: "text",
                width: 55},
            {name: "Date", type: "text", width: 110},
            {name: "Summary", type: "text"},
            {
                name: "Summary Photo", itemTemplate: function (value) {
                    return $("<img>").attr("src", value).css({width: 110});
                },width: 120,
                editTemplate: function(item,itemIndex) {
                    var editControl = this.insertControl = $("<input>").prop("type", "file").attr('id','summary_photo_edit_'+itemIndex.Id);
                    return editControl;
                },
                insertTemplate: function(item,itemIndex) {
                    var insertControl = this.insertControl = $("<input>").prop("type", "file").attr('id','summary_photo_insert_new');
                    return insertControl;
                },
                insertValue: function() {
                    return this.insertControl[0].files[0];
                },
            },
            {name: "Highlights", type: "text"},
            {name: "Referee", type: "text", width: 80},
            {name: "Squads",editing: false,inserting: false, type: "text", width: 100,
                itemTemplate: function (value,itemIndex) {
                    return $("<img>").attr("src", value).css({width: 80}).attr('id','show-squad-'+itemIndex.Id);
                }
            },
            {name: "Stadium", type: "select",
                items: db_games.stadiums,
                valueField: "ID",
                textField: "Name",
                width: 110
            },
            {type: "control"}
        ]
    });

});
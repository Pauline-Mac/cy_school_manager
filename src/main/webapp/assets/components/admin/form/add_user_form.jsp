<script src="../js/add_user.js"></script>
<form class="add-form" action="adduserpost" method="post">

    <div class="add-user-form-title">
        Ajouter un utilisateur
    </div>


    <table>
        <tr>
            <td><label for="last_name">Nom</label></td>
            <td><input name="last_name" id="last_name" class="add-user-input add-user-input-table"></td>
        </tr>
        <tr>
            <td><label for="first_name">Prénom</label></td>
            <td><input name="first_name" id="first_name" class="add-user-input add-user-input-table"></td>
        </tr>
        <tr>
            <td><label for="email">Email</label></td>
            <td><input name="email" id="email" type="email" class="add-user-input add-user-input-table"></td>
        </tr>
        <tr>
            <td><label for="tel">Numéro de téléphone</label></td>
            <td><input name="tel" id="tel" type="tel" class="add-user-input add-user-input-table"></td>
        </tr>
        <tr>
            <td><label for="birth_date">Date de naissance</label></td>
            <td><input name="birth_date" id="birth_date" type="date" class="add-user-input add-user-input-table"></td>
        </tr>
        <tr>
            <td><label for="username">Nom d'utilisateur</label></td>
            <td><input name="username" id="username" class="add-user-input add-user-input-table"></td>
        </tr>
        <tr>
            <td><label for="role">Statut</label></td>
            <td>
                <select name="role" id="role" class="add-user-input-select add-user-input-table">
                    <option value="student">Eleve</option>
                    <option value="professor">Professeur</option>
                </select>
            </td>
        </tr>
    </table>

    <div class="add-class">

        <h4>Ajouter des cours</h4>

        <div id="class-list">

        </div>

        <input type="text" id="add-class-input" class="add-user-input">
        <input type="button" onclick="addClass()" value="Ajouter" class="add-class-button add-user-input">




    </div>


    <input class="add-user-form-button add-user-input" type="submit" value="Confirmer">
</form>
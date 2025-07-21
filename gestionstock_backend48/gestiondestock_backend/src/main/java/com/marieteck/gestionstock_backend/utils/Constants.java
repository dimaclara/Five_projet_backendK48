package com.marieteck.gestionstock_backend.utils;

public interface Constants {

    public static String APP_ROOT = "/gestiondestock/v1";

    // Constantes pour les rôles
    public static String ADMIN_ROLE = "ADMIN";
    public static  String ENTERPRISE_USER_ROLE = "ENTERPRISE_USER";
    public static  String CLIENT_ROLE = "CLIENT";
    public static  String MANAGER_ROLE = "MANAGER";

    // Constantes pour les endpoints d'administration
    public static final String ADMIN_ENDPOINT = APP_ROOT + "/admin";
    public static final String TOKEN_MANAGEMENT_ENDPOINT = ADMIN_ENDPOINT + "/tokens";

   /** String COMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT + "/commandesfournisseurs";
    String CREATE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/create";
    String FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}";
    String FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/filter/{codeCommandeFournisseur}";
    String FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/all";
    String DELETE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}";

    String ENTREPRISE_ENDPOINT = APP_ROOT + "/enterprises";

    String FOURNISSEUR_ENDPOINT = APP_ROOT + "/fournisseurs";

    String UTILISATEUR_ENDPOINT = APP_ROOT + "/utilisateurs";

    String VENTES_ENDPOINT = APP_ROOT + "/ventes";**/
}

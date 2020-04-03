<?php
/**
 * Created by PhpStorm.
 * User: Aziz
 * Date: 22/02/2019
 * Time: 13:02
 */
/**
 *
 * @Route("commande")
 */

namespace ProduitBundle\Controller\Front;


use ProduitBundle\Entity\Commande;

use ProduitBundle\Entity\LigneCommande;
use Symfony\Component\Validator\Constraints\DateTime;
use UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Doctrine\DBAL\Exception\UniqueConstraintViolationException;
use ProduitBundle\Entity\PanierProduit;
use ProduitBundle\Entity\Produits;

use ProduitBundle\Entity\Categorie;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Config\Definition\Exception\Exception;

class CommandeController extends Controller
{

    /**
     * Finds and displays a user entity.
     *
     * @Route("/new/{prix_total}/{id_u}", name="ajout_commande")
     * @Method("GET")
     */
    public function newAction($prix_total,$id_u)
    {

        $em =$this->getDoctrine()->getManager();

         $liste_panier = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
        $users = $em->getRepository('UserBundle:User')->findAll();

         $commande=new Commande();
         $etat="En cours";
         $date_auj=new \DateTime('now');
         $commande->setIdUtilisateur($id_u);
         $commande->setPrixTotal($prix_total);
        $commande->setDateEmission($date_auj);
         $commande->setEtatCommande($etat);
         $em->persist($commande);
         $em->flush();
         $id_com=$commande->getId();


        foreach ($liste_panier as $article_panier)
         {
             $Ligne_commande=new LigneCommande();
             $prix=$article_panier->getPrix();
             $id_produit=$article_panier->getIdprod();
             $Ligne_commande->setIdprod($id_produit);
             $Ligne_commande->setPrixprod($prix);
             $Ligne_commande->setIdUtilisateur($id_u);
             $Ligne_commande->setIdCommande($id_com);

             $em->persist($Ligne_commande);
             $em->flush();
             $com_modif = $em->getRepository(Commande::class)->find($id_com);

         }

         $L=$em->getRepository(PanierProduit::class)->findAll();
         foreach ($L as $value)
         {
             $em->remove($value);
             $em->flush();
         }
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        $lignes = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
            $produits = $em->getRepository('ProduitBundle:Produits')->findAll();
         return $this->render('@Produit/Front/Commande/page_index_commande.html.twig',array(
             'commandes'=> $commandes,'lignes'=> $lignes,'produits'=> $produits));



        }
    /**
     * Finds and displays a user entity.
     *
     * @Route("/show_ligne/{id_c}", name="ligne_show")
     * @Method("GET")
     */
    public function showLigneAction($id_c)
    {
        $em =$this->getDoctrine()->getManager();
        $L=$em->getRepository(PanierProduit::class)->findAll();

        $produits = $em->getRepository('ProduitBundle:Produits')->findAll();
        $liste_ligne = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
        $tab=array('id_commande' => $id_c);

        return $this->render('@Produit/DashboardUser/detail_commande.html.twig',array(
           'liste_ligne'=> $liste_ligne,'produits'=> $produits,'tab'=>$tab));


    }

    /**
     * @Route("/annuler_commande/{id_c}", name="annuler_commande")
     * @Method("GET")
     */
    public function annulerAction($id_c)
    {
        $em=$this->getDoctrine()->getManager();
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        $lignes = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
        foreach ($commandes as $value)
        {
            if($value->getId()==$id_c)
            {
                $date_com=$value->getDateEmission();
                $DateNow = new \DateTime('now');;
                $TempsRestant = $DateNow->diff($date_com);
                if($TempsRestant->h >24 )
                {
                    echo "<script language=\"javascript\">alert(\"Vous ne pouvez pas annuler la commande car avez dépassé les 24 heures  \");</script>";

                }
                else
                {
                    foreach ($lignes as $v)
                    {
                        if($v->getIdCommande()==$id_c)
                        {
                            $em->remove($v);
                            $em->flush();
                        }
                    }
                    $em->remove($value);
                    $em->flush();

                }
            }

        }
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        return $this->render('@Produit/Front/Commande/page_index_commande.html.twig', array(
            'commandes'=> $commandes
        ));

        /*
        foreach ($commandes as $value)
        {
            if($value->getId()==$id_c)
            {
                foreach ($lignes as $v)
                {
                    if($v->getIdCommande()==$id_c)
                    {
                        $em->remove($v);
                        $em->flush();
                    }
                }
                $em->remove($value);
                $em->flush();
            }
        }*/


    }

    /**
     * @Route("/show_commande", name="show_commande")
     * @Method("GET")
     */
    public function showAction()
    {
        $em=$this->getDoctrine()->getManager();
        $commandes = $em->getRepository('ProduitBundle:Commande')->findAll();
        return $this->render('@Produit/DashboardUser/page_index_commande.html.twig', array(
            'commandes'=> $commandes
        ));
    }





    }


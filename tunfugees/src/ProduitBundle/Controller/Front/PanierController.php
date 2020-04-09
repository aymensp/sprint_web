<?php
/**
 * Created by PhpStorm.
 * User: Aziz
 * Date: 18/02/2019
 * Time: 14:49
 */

namespace ProduitBundle\Controller\Front;


/**
 *
 * @Route("panier")
 */

use Doctrine\DBAL\Exception\UniqueConstraintViolationException;
use ProduitBundle\Entity\PanierProduit;
use ProduitBundle\Entity\Produits;

use ProduitBundle\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Config\Definition\Exception\Exception;

class PanierController extends Controller
{
  public $i=0;

    /**
     * Finds and displays a user entity.
     *
     * @Route("/", name="panier_show")
     * @Method("GET")
     */
    public function showAction()
    {
        $em =$this->getDoctrine()->getManager();
        $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();

        $prixtotal=0;
        $somme=0;
        foreach ($liste as $value)
        {
            $prixtotal=$value->getPrix();
            $somme=$prixtotal+$somme;
        }

        $tab=array("somme"=>$somme);

        return $this->render('@Eco/Panier/page_index_panier.html.twig', array(
           'liste'=> $liste,'tab'=> $tab));

    }

    /**
     * Finds and displays a user entity.
     *
     * @Route("/show2", name="annonce_show")
     * @Method("GET")
     */
    public function show2Action()
    {

        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
        $produits = $em->getRepository('ProduitBundle:Produits')->findAll();


        return $this->render('@Produit/Front/Produit/produit.html.twig', array(
            "produits"=>$produits,'categories'=> $categories,
        ));

    }


    /**
     * Finds and displays a user entity.
     *
     * @Route("/new/{id_a}", name="ajout_annonce")
     * @Method("GET")
     */
    public function newAction($id_a)
    {
        $em =$this->getDoctrine()->getManager();
        $lignes = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
        foreach ($lignes as $value)
        {
            if($value->getIdProd()==$id_a)
            {
                echo "<script language=\"javascript\">alert(\"Vous ne pouvez pas Ajouter une produit déja passé en commande \");</script>";

                $em = $this->getDoctrine()->getManager();
                $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
                $produit = $em->getRepository('ProduitBundle:Produits')->findAll();
                $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
                $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
                return $this->render('@Produit/Front/Produit/produit.html.twig', array(
                    "produits"=>$produit,"lc"=>$lc,'categories'=> $categories,'liste'=> $liste,
                ));

            }
        }
        try{
            $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
            $produit = $em->getRepository('ProduitBundle:Produits')->findAll();
            $a = $em->getRepository('ProduitBundle:Produits')->find($id_a);
            $prix=$a->getPrix();
            $titre=$a->getNomprod();
            $nomref=$a->getNomref();


            $photo=$a->getImg();

            $L=new PanierProduit();
            $L->setIdprod($id_a);
            $L->setPrix($prix);
            $L->setNomprod($titre);

            $L->setNomref($nomref);

            $L->setImg($photo);

            $em->persist($L);
            $em->flush();

            $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
            $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
            return $this->render('@Produit/Front/Produit/produit.html.twig', array(
                "produits"=>$produit,'categories'=> $categories,"lc"=>$lc,'liste'=> $liste,
            ));

        }
        catch (UniqueConstraintViolationException $e)
        {
            echo "<script language=\"javascript\">alert(\"Produit Déja Ajouté au Panier \");</script>";

            $em = $this->getDoctrine()->getManager();
            $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
            $produit = $em->getRepository('ProduitBundle:Produits')->findAll();
            $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
            $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
            return $this->render('@Produit/Front/Produit/produit.html.twig', array(
                "produits"=>$produit,'categories'=> $categories,"lc"=>$lc,'liste'=> $liste,
            ));

        }

    }


    /**
     * @Route("/show3", name="annonce_show3")
     * @Method("GET")
     */
    public function indexTestAction()
    {
        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
        $produit = $em->getRepository('ProduitBundle:Produits')->findAll();
        $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();

        return $this->render('@Produit/Front/Produit/produit.html.twig', array(
            "produits"=>$produit,'categories'=> $categories,'liste'=> $liste,
        ));
    }

    /**
     * @Route("/suprimer/{id}", name="supprimer_ligne")
     * @Method("GET")
     */
    public function supprimerAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $panier_prodduit = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
        foreach ($panier_prodduit as $p)
        {
            if($p->getId()==$id)
            {
                $em->remove($p);
                $em->flush();
            }
        }


        $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();

        return $this->render('@Produit/Panier/page_index_panier.html.twig', array(
            'liste'=> $liste
        ));
    }

    /**
     * @Route("/vider_panier", name="vider_panier")
     * @Method("GET")
     */

    public function viderAction()
    {
        $em=$this->getDoctrine()->getManager();
        $L=$em->getRepository(PanierProduit::class)->findAll();
        foreach ($L as $value)
        {
            $em->remove($value);
            $em->flush();
        }
        $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();

        return $this->render('@Produit/Panier/page_index_panier.html.twig', array(
            'liste'=> $liste
        ));
    }





}
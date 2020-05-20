<?php
/**
 * Created by PhpStorm.
 * User: Aziz
 * Date: 01/05/2019
 * Time: 00:34
 */

namespace ProduitBundle\Controller\API;


use ProduitBundle\Entity\PanierProduit;
use ProduitBundle\Entity\Commande;

use ProduitBundle\Entity\LigneCommande;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ProduitBundle\Entity\Produits;
use ProduitBundle\Entity\Categorie;
use UserBundle\Entity\User;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


/**
 *
 * @Route("panierApi")
 */

class PanierApiController extends Controller
{


    /**
     * @Route("/new", name="panier_api_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $annoncep = new PanierProduit();
        $annoncep->setIdprod($request->get('idProd'));
        $annoncep->setNomprod($request->get('nomProd'));
        $annoncep->setNomref($request->get('nomRef'));
        $annoncep->setPrix($request->get('prix'));

        $annoncep->setImg($request->get('img'));
        $em->persist($annoncep);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($annoncep);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/", name="getAll_annoncePanier")
     */
    public function getall()
    {
        $task = $this->getDoctrine()->getManager()
            ->getRepository(PanierProduit::class)
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($task);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/vider", name="panier_api_vider")
     * @Method({"GET", "POST"})
     */
    public function viderAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $L=$em->getRepository(PanierProduit::class)->findAll();
        foreach ($L as $value)
        {
            $em->remove($value);
            $em->flush();
        }

        $serializer = $this->get('jms_serializer');

        $response = new Response($serializer->serialize($L, 'json'));
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }



    /**
     * @Route("/delete_a", name="panier_api_supp")
     * @Method({"GET", "POST"})
     */
    public function deleteAction(Request $request)
    {

       $id=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $annonce_panier = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
        foreach ($annonce_panier as $p)
        {
            if($p->getId()==$id)
            {
                $em->remove($p);
                $em->flush();
            }
        }

        $serializer = $this->get('jms_serializer');

        $response = new Response($serializer->serialize($annonce_panier, 'json'));
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/deletecmd", name="panier_api_suppcmd")
     * @Method({"GET", "POST"})
     */
    public function deleteCommande(Request $request)
    {

        $id=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $annonce_panier = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
        $annonce_panier2= $em->getRepository('ProduitBundle:Commande')->findAll();
        foreach ($annonce_panier as $p)
        {
            if($p->getIdCommande()==$id)
            {
                $em->remove($p);
                $em->flush();
            }
        }

        foreach ($annonce_panier2 as $y)
        {
            if($y->getId()==$id)
            {
                $em->remove($y);
                $em->flush();
            }
        }


        $serializer = $this->get('jms_serializer');

        $response = new Response($serializer->serialize($annonce_panier, 'json'));
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }
    /**
     * @Route("/newcmd", name="panier_api_newcmd")
     * @Method({"GET", "POST"})
     */
    public function newCMDAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $commande=new Commande();
        $date_auj=new \DateTime('now');
        $commande->setIdUtilisateur($request->get('id_utilisateur'));
        $id_u1=$commande->getIdUtilisateur();
        $commande->setPrixTotal($request->get('prix_total'));
        $commande->setDateEmission($date_auj);
        $commande->setEtatCommande($request->get('etat_commande'));
        $em->persist($commande);
        $em->flush();

        $id_com=$commande->getId();
        $id_u=$commande->getIdUtilisateur();
        $liste_panier = $em->getRepository('ProduitBundle:PanierProduit')->findAll();

        foreach ($liste_panier as $article_panier)
        {
            $Ligne_commande=new LigneCommande();
            $prix=$article_panier->getPrix();
            $id_annonce=$article_panier->getIdProd();
            $Ligne_commande->setIdprod($id_annonce);
            $Ligne_commande->setPrixprod($prix);
            $Ligne_commande->setIdUtilisateur($id_u);
            $Ligne_commande->setIdCommande($id_com);

            $em->persist($Ligne_commande);
            $em->flush();


        }

        $L=$em->getRepository(PanierProduit::class)->findAll();
        foreach ($L as $value)
        {
            $em->remove($value);
            $em->flush();
        }
        $serializer = $this->get('jms_serializer');
        $response = new Response($serializer->serialize($L, 'json'));
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/getcmd", name="getAll_commandes")
     */
    public function getall2()
    {
        $task = $this->getDoctrine()->getManager()
            ->getRepository(Commande::class)
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($task);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/getcmdclient", name="getcmdClient")
     */
    public function getcmdclient(Request $request)
    {
        $id=$request->get('id_user');
        $task = $this->getDoctrine()->getManager()->getRepository('ProduitBundle:Commande')
            ->findBy(array('idUtilisateur'=> $id));
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($task);
        return new JsonResponse($formatted);

    }









    /**
     * @Route("/getligne", name="getAllLignes")
     */
    public function getLignes()
    {

        $task = $this->getDoctrine()->getManager()
            ->getRepository(LigneCommande::class)
            ->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($task);
        return new JsonResponse($formatted);

    }








}
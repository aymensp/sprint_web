<?php

namespace ProduitBundle\Controller\Front;

use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use ProduitBundle\Entity\Produits;
use ProduitBundle\Entity\Categorie;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * @Route("annonce")
 */
class FProduitsController extends Controller
{
    /**
     * @Route("/", name="f_annonce_index")
     * @Method({"GET", "POST"})
     */
    public function indexAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
        $dql   = "SELECT a FROM ProduitBundle:Produits a";
        $query = $em->createQuery($dql);
        $paginator= $this->get('knp_paginator');
       $produit= $pagination = $paginator->paginate(
            $query, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3 /*limit per page*/
        );
        $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
        $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();

        $likes = $em->getRepository('ProduitBundle:Produits')->likeProduit();
        return $this->render
        ('@Produit/Front/Produit/produitPagination.html.twig',
            array(
            'produits' => $produit ,'liste' => $liste,'lc' => $lc, 'categories' => $categories, 'likes' => $likes,
        ));
    }

    /**
     * Finds and displays a user entity.
     *
     * @Route("/{id}", name="f_annonce_show")
     * @Method("GET")
     */
    public function showAction(Produits $produit)
    {

        $produit->setViews($produit->getViews() + 1);
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        return $this->render('@Produit/Front/Produit/show.html.twig', array(
            'produit' => $produit,
        ));
    }

    /**
     * @Route("/categorie/{cat}", name="f_recherch_Categorie")
     * @Method("GET")
     */
    public function RecherchCategorieAction(Request $request, $cat)
    {

        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
        $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
        $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
        $produit = new Produits();
        $produit = $em->getRepository('ProduitBundle:Produits')->findByCategorie($cat);

        return $this->render('@Produit/Front/Produit/produit.html.twig', array(
            "produits" => $produit, 'categories' => $categories,'liste' => $liste,'lc' => $lc

        ));
    }

    /**
     * Creates a new Categorie et produit entity.
     *
     * @Route("/jaime/new", name="f_annonce_jaime")
     * @Method({"GET", "POST"})
     */
    public function newJaimeAction(Request $request)
    {

        if (($request->isXmlHttpRequest())) {
            $id = $request->get('Id');
            $em = $this->getDoctrine()->getManager();
            $produit = $em->getRepository('ProduitBundle:Produits')->find($id);
            $produit->setLikes($produit->getLikes() + 1);
            $em->flush();
            return $this->redirectToRoute('du_produit_index');

        }
    }

    /**
     * @Route("/trier/{val}", name="f_annonce_trier")
     * @Method("GET")
     */
    public function TrierAction(Request $request)
    {

        $val = $request->get('val');
        //dump($val);exit();
        if ($val == 'PE') {
            $em = $this->getDoctrine()->getManager();
            $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
            $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
            $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
            $produits = $em->getRepository('ProduitBundle:Produits')->trierPrixElv();
            $likes = $em->getRepository('ProduitBundle:Produits')->likeProduit();
        } elseif ($val == 'PB') {
            $em = $this->getDoctrine()->getManager();
            $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
            $liste = $em->getRepository('ProduitBundle:PanierProduit')->findAll();
            $lc = $em->getRepository('ProduitBundle:LigneCommande')->findAll();
            $produits = $em->getRepository('ProduitBundle:Produits')->trierPrixBas();
            $likes = $em->getRepository('ProduitBundle:Produits')->likeProduit();

        }
        return $this->render('@Produit/Front/Produit/produit.html.twig', array(
            "produits" => $produits, 'categories' => $categories, 'likes' => $likes,'liste' => $liste,'lc' => $lc
        ));
    }
    /**
     * @Route("/pdf/{id}", name="pdf")
     */

    public function pdfAction(Request $request)
    {
        $val = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository('ProduitBundle:Produits')->find($val);


        $snappy = $this->get('knp_snappy.pdf');

        $html = $this->renderView('@Eco/Front/Produit/produitPdf.html.twig', array(
            'produit' => $produit,
        ));

        $filename = 'Produit';

        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'inline; filename="'.$filename.'.pdf"'
            )
        );
    }
    /**
     *
     * @Route("/annonce/recherche", name="f_recherche")
     * @Method({"GET", "POST"})
     */
    public function rechercheAction(Request $request)
    {
            $keyWord = $request->get('keyWord');
           // dump($keyWord);
        if($keyWord == '')
        {
            $produit = $this->getDoctrine()->getRepository('ProduitBundle:Produits')->findAll();
        }else
        {
            $produit = $this->getDoctrine()->getRepository('ProduitBundle:Produits')->RechercheTitreProduit($keyWord);

        }

            $template = $this->render( '@Produit/Front/Produit/Recherche.html.twig', array("produits" => $produit))->getContent();
            $json     = json_encode($template);
            $response = new Response($json, 200);
            $response->headers->set('Content-Type', 'application/json');

            return $response;
    }



}

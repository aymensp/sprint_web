<?php

namespace RefugiesBundle\Controller;

use RefugiesBundle\Entity\Refugies;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\Request;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;

/**
 *
 * @Route("/front")
 */

class RefugiesFrontController extends Controller
{
    /**
     * @Route("/evenement", name="front_evenements_index")
     * @Method("GET")
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $products = $em->getRepository('RefugiesBundle:Refugies')->findAll();
        $dql   = "SELECT a FROM RefugiesBundle:Refugies a";
        $query = $em->createQuery($dql);
        $paginator= $this->get('knp_paginator');
        $products= $pagination = $paginator->paginate(
            $query, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3 /*limit per page*/
        );

        return $this->render('@Refugies/index.html.twig', array(
            'Refugies' => $products,
        ));
    }
    /**
     * @Route("/evenement/{idref}", name="front_evenements_show")
     * @Method("GET")
     */
    public function showAction(Refugies $refugies)
    {
        $em = $this->getDoctrine()->getManager();
        $em->flush();

        return $this->render('@Refugies/show.html.twig', array(
            'Refugies' => $refugies,

        ));


    }
    /**
     * @Route("/trier/{val}", name="f_ref_trier")
     * @Method("GET")
     */
    public function TrierAction(Request $request)
    {

        $val = $request->get('val');
        //dump($val);exit();
        if ($val == 'PE') {
            $em = $this->getDoctrine()->getManager();

            $produits = $em->getRepository('RefugiesBundle:Refugies')->trierageElv();

        } elseif ($val == 'PB') {
            $em = $this->getDoctrine()->getManager();

            $produits = $em->getRepository('RefugiesBundle:Refugies')->trierageBas();


        }
        return $this->render('@Refugies/indexT.html.twig', array(
            "produits" => $produits));
    }




}

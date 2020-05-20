<?php

namespace RefugiesBundle\Controller;

use CampsBundle\Entity\Camps;
use EcoBundle\Entity\Annonce;
use EcoBundle\Entity\PublicationForum;
use RefugiesBundle\Entity\Refugies;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\Request;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
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
    /**
     * @Route("/Ref", name="aa_api_index")
     * @Method("GET")
     */
    public function indexApiAction()
    {
        $pubs = $this->getDoctrine()->getManager()
            ->getRepository(Refugies::class)
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($pubs);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/back/delete/{id}", name="ref_api_delete")
     * @Method("GET")
     */
    public function jsondeleteAction($id)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository(Refugies::class)
            ->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($publication);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($publication);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/new/{Nom}/{prenom}/{age}/{pays}/{image}"), name="ref_api_new")
     * @Method({"GET", "POST"})
     */
    public function jsonaddAction(Request $request, String $Nom,String $prenom,int $age , String $pays ,String $image)
    {
        $events= new Refugies();
        $em = $this->getDoctrine()->getManager();

        $events->setNom($Nom);
        $events->setPrenom($prenom);
        $events->setPays($pays);

        $events->setImage($image);
    $events->setAge($age);
        $Categories= ['null'];
        switch ($age) {
            case  (0<=$age && $age<=3) :
                $Categories=['Infancy(0-3)'];
                break;
            case (4<=$age && $age<=8):
                $Categories=['Childhood(4-8)'];
                break;
            case (9<=$age && $age<=13):
                $Categories=['Puberty(9-13)'];
                break;
            case (14<=$age && $age<=18):
                $Categories=['Teenager(14-18)'];
                break;
            case (19<=$age && $age<=39):
                $Categories=['Adult(19-39)'];
                break;
            case (40<=$age && $age<=59):
                $Categories=['Middle age(40-59)'];
                break;
            case (60<=$age && $age<=200):
                $Categories=['old age(60-99)'];
                break;
                return $Categories;
        }
        $array_projet=$em->getRepository(Camps::class)->findBy(array('Categories' => $Categories));

            $one_projet_objet=$array_projet[0];
            $events->setCamps($one_projet_objet);


            $em->persist($events);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    /**
     * Finds and displays a user entity.
     *
     * @Route("/viwes/{id}/{Nom}/{prenom}/{pays}/{age}", name="ref_json_viwe")
     * @Method({"GET", "POST"})
     */

    public function jsoneditAction(int $id,String $Nom,String $prenom,String $pays,int $age)
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('RefugiesBundle:Refugies')->find($id);

        $events->setNom($Nom);
        $events->setPrenom($prenom);

        $events-> setPays($pays);
        $events->setAge($age);
        $Categories= ['null'];
        switch ($age) {
            case  (0<=$age && $age<=3) :
                $Categories=['Infancy(0-3)'];
                break;
            case (4<=$age && $age<=8):
                $Categories=['Childhood(4-8)'];
                break;
            case (9<=$age && $age<=13):
                $Categories=['Puberty(9-13)'];
                break;
            case (14<=$age && $age<=18):
                $Categories=['Teenager(14-18)'];
                break;
            case (19<=$age && $age<=39):
                $Categories=['Adult(19-39)'];
                break;
            case (40<=$age && $age<=59):
                $Categories=['Middle age(40-59)'];
                break;
            case (60<=$age && $age<=200):
                $Categories=['old age(60-99)'];
                break;
                return $Categories;
        }
        $array_projet=$em->getRepository(Camps::class)->findBy(array('Categories' => $Categories));

        $one_projet_objet=$array_projet[0];
        $events->setCamps($one_projet_objet);
        //$events->setImage($image);



        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/Camps/{id}", name="camp_api_index")
     * @Method("GET")
     */
    public function jsonlistAction($id)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository(Refugies::class)
            ->find($id);

        $volontaire= $publication->getCamps();
        $c=$volontaire;
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($c);
        return new JsonResponse($formatted);
    }


}

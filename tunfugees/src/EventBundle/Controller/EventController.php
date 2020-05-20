<?php

namespace EventBundle\Controller;


use EcoBundle\Entity\CategorieEvts;
use EcoBundle\Entity\Evenement;
use EventBundle\Entity\Event;
use EventBundle\Entity\Volontaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use ADesigns\CalendarBundle\Event\CalendarEvent;
use ADesigns\CalendarBundle\Entity\EventEntity;
use Doctrine\ORM\EntityManager;

class EventController extends Controller
{

    public function eventAction(Request $request)
    {
        $events= $this->getDoctrine()
            ->getRepository(Event::class)->findAll();

            $em = $this->getDoctrine()->getManager();

            $products = $em->getRepository('EventBundle:Event')->findAll();
            $dql   = "SELECT a FROM EventBundle:Event a";
            $query = $em->createQuery($dql);
            $paginator= $this->get('knp_paginator');
            $events= $pagination = $paginator->paginate(
                $query, /* query NOT result */
                $request->query->getInt('page', 1), /*page number*/
                3 /*limit per page*/
            );





        return $this->render('@Event/Event/event.html.twig',array('events'=>$events));
    }

    public function ajouteventAction()
    {
        $events= $this->getDoctrine()
            ->getRepository(Event::class)->findAll();


        return $this->render('@Event/DashboardAdmin/Event/ajoutevent.html.twig',array('events'=>$events));
    }


    public function deleteEventAction($id)
    {
        $m=$this->getDoctrine()->getManager();
        $evenement = $m->getRepository(Event::class)->find($id);
        $m->remove($evenement);
        $m->flush();

        return$this->redirectToRoute('ajoutevent');
    }


    public function ajouterAction(Request $request)
    {

        $categorieEvts = new Event();
        $formCateg = $this->createForm('EventBundle\Form\EventType', $categorieEvts);
        $formCateg->handleRequest($request);

        if ($formCateg->isSubmitted() && $formCateg->isValid()) {


            $em = $this->getDoctrine()->getManager();
            $em->persist($categorieEvts);

            $em->flush();
            return $this->redirectToRoute('ajouter');
        }

        return $this->render('@Event/DashboardAdmin/Event/ajouter.html.twig', array(
            'formCateg' => $formCateg->createView(),
        ));
    }
    public function editAction(Request $request,Event $evenement)
    {


        $editForm = $this->createForm('EventBundle\Form\EventType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            /**
             * @var UploadedFile $file
             */
            $file = $evenement->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move(
                $this->getParameter('image_directory'),$fileName
            );
            $evenement->setImage($fileName);

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('edit', array('id' => $evenement->getId()));
        }

        return $this->render('@Event/DashboardAdmin/Event/edit.html.twig', array(
            'evenement' => $evenement,
            'form' => $editForm->createView(),
        ));
    }
    public function detailsAction($id)
    {
        $m=$this->getDoctrine();
        $events = $m->getRepository(Event::class)->find($id);
        return $this->render('@Event/Event/details.html.twig', array('events' => $events));
    }
    public function descriptionAction($id)
    {
        $m=$this->getDoctrine();
        $events = $m->getRepository(Event::class)->find($id);
        return $this->render('@Event/DashboardAdmin/Event/description.html.twig', array('events' => $events));
    }
    public function CalendarHomeAction()
    {
        $events= $this->getDoctrine()
            ->getRepository(Event::class)->findAll();
        return $this->render('@Event/Event/CalendarHome.html.twig',array('events' => $events));
    }














}



<?php

namespace EventBundle\Controller;


use EventBundle\Entity\Event;
use EventBundle\Entity\Volontaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class VolontaireController extends Controller
{

    public function showAction()
    {
        $volontaires= $this->getDoctrine()
            ->getRepository(Volontaire::class)->findAll();
        return $this->render('@Event/DashboardAdmin/Volontaire/show.html.twig',array('volontaires'=>$volontaires));
    }
    public function deleteVoloAction($id)
    {
        $m=$this->getDoctrine()->getManager();
        $evenement = $m->getRepository(Volontaire::class)->find($id);
        $m->remove($evenement);
        $m->flush();

        return$this->redirectToRoute('show');
    }
    public function editVoloAction(Request $request,Volontaire $evenement)
    {


        $editForm = $this->createForm('EventBundle\Form\VolontaireType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('edit', array('id' => $evenement->getIdVol()));
        }

        return $this->render('@Event/DashboardAdmin/Volontaire/editVolo.html.twig', array(
            'evenement' => $evenement,
            'form' => $editForm->createView(),
        ));
    }

    public function ajoutVoloAction(Request $request,$id)
    {

        $categorieEvts = new Volontaire();
        $m=$this->getDoctrine();
        $events = $m->getRepository(Event::class)->find($id);
        $formCateg = $this->createForm('EventBundle\Form\VolontaireType', $categorieEvts);
        $formCateg->handleRequest($request);


        if ($formCateg->isSubmitted() && $formCateg->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $evenement = $em->getRepository('EventBundle:Event')->find($id);

            $categorieEvts->setNomEvent($evenement->getNomEvent());

            $em->persist($categorieEvts);
            $evenement->addVolontaires($categorieEvts);
            $em->persist($categorieEvts);
            $em->persist($evenement);
            $em->flush();
            return $this->redirectToRoute('event');
        }

        return $this->render('@Event/Volontaire/ajoutvolo.html.twig', array(
            'formCateg' => $formCateg->createView(),array('events' => $events)
        ));
    }
    public function cancelVoloAction(Request $request,$id,$id1)
    {

     //   $categorieEvts = new Volontaire();
            $em = $this->getDoctrine()->getManager();
            $evenement = $em->getRepository('EventBundle:Event')->find($id);
        $volontaire = $em->getRepository('EventBundle:Volontaire')->find($id1);

            $evenement->removeVolontaires($volontaire);
            $em->remove($volontaire);
            $em->persist($evenement);
            $em->flush();



        return$this->redirectToRoute('event');
    }







}

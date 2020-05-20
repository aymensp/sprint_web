<?php

namespace EventBundle\Controller;

use EventBundle\Entity\Event;
use EventBundle\Entity\Volontaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

use Symfony\Component\HttpFoundation\Response;

use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Validator\Constraints\DateTime;


class EventAPIController extends Controller
{
    public function allAction()
    {
        $event = $this->getDoctrine()->getManager()
            ->getRepository('EventBundle:Event')->findAll();
        $serializer = new Serializer([new DateTimeNormalizer('d-M-Y'),new ObjectNormalizer()]);
        //$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
      return new JsonResponse($formatted);

    }


    public function jsonaddAction(Request $request, String $Nom,String $adresse,String $date , String $description ,int $nbr,String $image)
    {
        $events= new Event();
        $em = $this->getDoctrine()->getManager();


        $events->setNomevent($Nom);
        $events->setAdresse($adresse);

        $events->setDate(new \DateTime('now'));
        $events->setDescription($description);
        $events-> setNbrmax($nbr);

        $events->setImage($image );

        $em->persist($events);
        $em->flush();
        $serializer = new Serializer([new DateTimeNormalizer('d-M-Y'),new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    public function jsondeleteAction($id)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository(Event::class)
            ->find($id);
        $em = $this->getDoctrine()->getManager();
            $em->remove($publication);
            $em->flush();
        $serializer = new Serializer([new DateTimeNormalizer('d-M-Y'),new ObjectNormalizer()]);
        $formatted = $serializer->normalize($publication);
        return new JsonResponse($formatted);
    }
    public function jsoneditAction(int $id,String $Nom,String $adresse,String $date,String $description,int $nbr,String $image)
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('EventBundle:Event')->find($id);

        $events->setNomevent($Nom);
        $events->setAdresse($adresse);
        $events->setDescription($description);
        $events-> setNbrmax($nbr);
        //$events-> setNbrmax($date);
        $events->setImage($image);



        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    public function jsonlistAction($id)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository(Event::class)
            ->find($id);

       $volontaire= $publication->getVolontaires();
        $serializer = new Serializer([new DateTimeNormalizer('d-M-Y'),new ObjectNormalizer()]);
        $formatted = $serializer->normalize($volontaire);
        return new JsonResponse($formatted);
    }
    public function jsonlist2Action()
    {
        $event = $this->getDoctrine()->getManager()
            ->getRepository('EventBundle:Volontaire')->findAll();
        $serializer = new Serializer([new DateTimeNormalizer('d-M-Y'),new ObjectNormalizer()]);
        //$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
        return new JsonResponse($formatted);
    }
    public function jsonedit2Action(int $id,String $presence)
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('EventBundle:Volontaire')->find($id);

        $events->setPresence($presence);




        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    public function jsonadd2Action(Request $request, $id,String $Nom,String $Prenom,String $mail , String $nomevent ,int $tel)
    {
        $events= new Volontaire();
        $m=$this->getDoctrine();
        $event = $m->getRepository(Event::class)->find($id);
        $em = $this->getDoctrine()->getManager();

        $events->setNom($Nom);
        $events->setPrenom($Prenom);

        $events->setCin(0);
        $events->setMail($mail);
        $events-> setNomEvent($nomevent);
        $events->setTel($tel);

        $events->setEtat("1");
        $events->setPresence("Absent");

        $event->addVolontaires($events);
        $em->persist($events);
        $em->persist($event);
        $em->flush();
        $serializer = new Serializer([new DateTimeNormalizer('d-M-Y'),new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }
    public function verifAction ($id,$mail)
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('EventBundle:Event')->find($id);
        $c= $em->getRepository('EventBundle:Volontaire')->findBy(array('mail'=> $mail));

        $vol = $c[0];
        if($vol == null) return new JsonResponse(null);
        else{
            if($vol->getNomEvent()!=$events->getNomevent()) return new JsonResponse(null);
            else{

                return new JsonResponse(true);
            }
        }


    }
    public function jmscancelAction($id,$mail)
    {
        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository('EventBundle:Event')->find($id);
        $volontaire = $em->getRepository('EventBundle:Volontaire')->findOneBy(array('mail' => $mail));

        $evenement->removeVolontaires($volontaire);
        $em->remove($volontaire);
        $em->persist($evenement);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($volontaire);
        return new JsonResponse($formatted);

    }

}
